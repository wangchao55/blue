package com.sky.cold.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.sky.cold.bo.AdminUserDetails;
import com.sky.cold.cache.service.AdminUserCacheService;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.service.RedisService;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.dao.AdminDao;
import com.sky.cold.dao.AdminRoleRelationDao;
import com.sky.cold.dto.AdminInfoDto;
import com.sky.cold.entity.*;
import com.sky.cold.security.util.JWTTokenUtil;
import com.sky.cold.service.AdminRoleRelationService;
import com.sky.cold.service.AdminService;
import com.sky.cold.service.MenuService;
import com.sky.cold.vo.AdminPasswordVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    AdminDao adminDao;

    @Autowired
    AdminRoleRelationService adminRoleRelationService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AdminUserCacheService adminUserCacheService;

    @Autowired
    RedisService redisService;

    @Autowired
    Producer producer;

    @Autowired
    MenuService menuService;

    @Autowired
    JWTTokenUtil jwtTokenUtil;

    /**
     * 注册
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(Admin admin) {
        //查询该用户名是否已注册
        Admin info = new Admin().selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUsername, admin.getUsername()));
        ApiAssert.isNull(ErrorCodeEnum.USERNAME_ALREADY_EXISTS,info);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setCreateTime(new Date());
        admin.setStatus(1);
        boolean insert = admin.insert();
        if(insert){
            //用户信息存入缓存
            adminUserCacheService.saveAdminInfoByRedis(admin);
        }
        return insert;
    }



    /**
     * 登录
     */
    @Override
    public Map<String,Object> login(Admin admin) {
        String token = null;
        try {
            //获取security用户详情
            UserDetails userDetails = loadUserByUserName(admin.getUsername());
            if(!passwordEncoder.matches(admin.getPassword(),userDetails.getPassword())){
                //密码错误
                ApiAssert.failure(ErrorCodeEnum.USERNAME_OR_PASSWORD_IS_WRONG);
            }
            if(!userDetails.isEnabled()){
                //账号禁用
                ApiAssert.failure(ErrorCodeEnum.USER_IS_DISABLED);
            }
            //用户名和密码验证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = adminUserCacheService.getAdminToken(userDetails.getUsername());
            if(StringUtils.isEmpty(token)){
                //生成token
                token = jwtTokenUtil.generateToken(userDetails);
                //存入缓存
                adminUserCacheService.saveAdminToken(userDetails.getUsername(),token);
            }
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("token",token);
        map.put("tokenHead",tokenHead);
        return map;
    }

    /**
     * 通过用户名加载security用户信息
     */
    @Override
    public UserDetails loadUserByUserName(String userName){
        //获取用户信息
        Admin admin = getAdminInfoByUserName(userName);
        //获取用户资源列表
        List<Resource> resourceList = getResourceListByUserInfo(admin.getId());
        return new AdminUserDetails(admin,resourceList);
    }

    /**
     * 获取该用户下的资源列表
     */
    private List<Resource> getResourceListByUserInfo(Long adminId) {
        //从缓存中获取
        List<Resource> resourceList = adminUserCacheService.getResourceList(adminId);
        if(CollectionUtils.isNotEmpty(resourceList)){
            return resourceList;
        }
        resourceList = adminRoleRelationService.getRosourceListByAdminId(adminId);
        if(CollectionUtils.isNotEmpty(resourceList)){
            //存入缓存
            adminUserCacheService.setResourceList(adminId,resourceList);
        }
        return resourceList;
    }

    /**
     * 通过用户名获取用户信息
     */
    @Override
    public AdminInfoDto getAdminUserInfoByUserName(String userName) {
        Admin admin = this.getAdminInfoByUserName(userName);
        //获取用户资源列表
        Long adminId = admin.getId();
        List<Resource> resourceList = this.getResourceListByUserInfo(adminId);
        //获取该用户角色
        List<Role> roleList = this.getAdminRoleInfo(adminId);
        //获取用户菜单列表
        List<Menu> menuList = adminRoleRelationService.getMenuListByAdminId(adminId);
        AdminInfoDto adminInfoDto = new AdminInfoDto();
        adminInfoDto.setAdmin(admin);
        adminInfoDto.setMenuList(menuList);
        adminInfoDto.setRoleList(roleList);
        adminInfoDto.setResourceList(resourceList);
        return adminInfoDto;
    }

    private Admin getAdminInfoByUserName(String userName){
        //从缓存中获取用户信息
        Admin admin = adminUserCacheService.getAdminInfoByRedis(userName);
        if (admin != null) {
            return admin;
        }
        //查询用户信息
        admin = new Admin().selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUsername, userName));
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND,admin);
        return admin;
    }

    /**
     * 刷新token
     */
    @Override
    public Map<String, Object> refreshToken(String oldToken) {
        HashMap<String, Object> map = new HashMap<>(16);
        String refreshHeadToken = jwtTokenUtil.refreshHeadToken(oldToken);
        ApiAssert.notNull(ErrorCodeEnum.TOKEN_HAS_EXPIRED,refreshHeadToken);
        map.put("token",refreshHeadToken);
        map.put("tokenHead",tokenHead);
        return map;
    }

    /**
     * 通过id获取用户基本信息
     */
    @Override
    public Admin getUserInfo(Long id) {
        Admin admin = new Admin().selectById(id);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND,admin);
        return admin;
    }

    /**
     * 退出登录
     */
    @Override
    public Void logout(String userName) {
        //清空缓存
        return adminUserCacheService.delAdminToken(userName);
    }

    /**
     * 查询列表
     */
    @Override
    public IPage<Admin> getAdminInfoList(String keyword, Integer pageNum, Integer pageSize) {
        IPage<Admin> page = new Page<>(pageNum,pageSize);
        return new Admin().selectPage(page,new QueryWrapper<Admin>().lambda().like(StringUtils.isNotBlank(keyword), Admin::getUsername, keyword).orderByDesc(Admin::getCreateTime));
    }

    /**
     * 修改用户信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateAdminInfo(Admin admin) {
        Admin info = this.getUserInfo(admin.getId());
        adminUserCacheService.delAdminInfoByRedis(info.getUsername());
        return admin.updateById();
    }

    /**
     * 批量删除用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAdminInfo(String ids) {
        Stream.of(ids.split(",")).map(Long::parseLong).collect(Collectors.toList()).stream().map(id -> {
            Admin admin = null;
            boolean flag = false;
            try {
                admin = this.getUserInfo(id);
                admin.setStatus(admin.getStatus() == 0 ? 1 : 0);
                flag = admin.updateById();
                if(flag){
                    adminUserCacheService.delAdminInfoByRedis(admin.getUsername());
                    adminUserCacheService.delResourceListByAdminId(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("删除用户失败:{}",id);
            }
            return flag;
        }).collect(Collectors.toList());
        return true;
    }

    /**
     * 修改密码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updatePassword(AdminPasswordVo adminPasswordVo, String userName) {
        Admin admin = this.getAdminInfoByUserName(userName);
        if(!passwordEncoder.matches(adminPasswordVo.getOldPassword(),admin.getPassword())){
            //密码错误
            ApiAssert.failure(ErrorCodeEnum.ORIGINAL_PASSWORD_IS_INCORRECT);
        }
        if(!adminPasswordVo.getNewPassword().equals(adminPasswordVo.getConfirmNewPassword())){
           ApiAssert.failure(ErrorCodeEnum.NEW_PASSWORD_NOT_CONSISTENT);
        }
        admin.setPassword(passwordEncoder.encode(adminPasswordVo.getNewPassword()));
        return admin.updateById();
    }

    /**
     * 给用户分配角色
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean distributionAdminRoleRelated(String roleIds, Long adminId) {
        //删除原有用户与角色关联
        new AdminRoleRelation().delete(Wrappers.<AdminRoleRelation>query().lambda().eq(AdminRoleRelation::getAdminId,adminId));
        Stream.of(roleIds.split(",")).map(Long::parseLong).collect(Collectors.toList()).stream().map(roleId ->{
                AdminRoleRelation adminRoleRelation = new AdminRoleRelation();
                adminRoleRelation.setAdminId(adminId);
                adminRoleRelation.setRoleId(roleId);
                return adminRoleRelation.insert();
        }).collect(Collectors.toList());
        //清除该用户的资源列表
        adminUserCacheService.delResourceListByAdminId(adminId);
        return true;
    }

    /**
     * 获取用户角色信息
     */
    @Override
    public List<Role> getAdminRoleInfo(Long adminId) {
        return new AdminRoleRelation().selectList(Wrappers.<AdminRoleRelation>query().lambda()
                .select(AdminRoleRelation::getRoleId)
                .eq(AdminRoleRelation::getAdminId, adminId))
                .stream().map(AdminRoleRelation::getRoleId).collect(Collectors.toList())
                .stream().map(roleId -> new Role().selectOne(Wrappers.<Role>query().lambda().eq(Role::getId,roleId).eq(Role::getStatus,1))).collect(Collectors.toList());
    }

    /**
     * 生成验证码
     */
    @Override
    public Object createKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());
        // 存储到redis中
        redisService.hSet("captcha", key, code, 120);
        //log.info("验证码 -- {} - {}", key, code);
        return MapUtil.builder()
                .put("token", key)
                .put("base64Img", base64Img)
                .build();
    }

}