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
import com.sky.cold.common.entity.dto.UserInfoDto;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.service.RedisService;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.dao.AdminDao;
import com.sky.cold.dto.AdminInfoDto;
import com.sky.cold.entity.*;
import com.sky.cold.service.AdminRoleRelationService;
import com.sky.cold.service.AdminService;
import com.sky.cold.service.MenuService;
import com.sky.cold.util.JWTTokenUtil;
import com.sky.cold.vo.AdminPasswordVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@RequiredArgsConstructor
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private final AdminRoleRelationService adminRoleRelationService;
    private final AdminUserCacheService adminUserCacheService;
    private final RedisService redisService;
    private final Producer producer;
    private final JWTTokenUtil jwtTokenUtil;

    /**
     * ??????
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(Admin admin) {
        //?????????????????????????????????
        Admin info = new Admin().selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUsername, admin.getUsername()));
        ApiAssert.isNull(ErrorCodeEnum.USERNAME_ALREADY_EXISTS,info);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setCreateTime(new Date());
        admin.setStatus(1);
        boolean insert = admin.insert();
        if(insert){
            //????????????????????????
            adminUserCacheService.saveAdminInfoByRedis(admin);
        }
        return insert;
    }



    /**
     * ??????
     */
    @Override
    public Map<String,Object> login(Admin admin) {
        String token = null;
        try {
            //??????security????????????
            UserDetails userDetails = loadUserByUserName(admin.getUsername());
            if(!passwordEncoder.matches(admin.getPassword(),userDetails.getPassword())){
                //????????????
                ApiAssert.failure(ErrorCodeEnum.USERNAME_OR_PASSWORD_IS_WRONG);
            }
            if(!userDetails.isEnabled()){
                //????????????
                ApiAssert.failure(ErrorCodeEnum.USER_IS_DISABLED);
            }
            //????????????????????????
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = adminUserCacheService.getAdminToken(userDetails.getUsername());
            if(StringUtils.isEmpty(token)){
                //??????token
                token = jwtTokenUtil.generateToken(userDetails);
                //????????????
                adminUserCacheService.saveAdminToken(userDetails.getUsername(),token);
            }
        } catch (AuthenticationException e) {
            LOGGER.warn("????????????:{}", e.getMessage());
        }
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("token",token);
        map.put("tokenHead",tokenHead);
        return map;
    }

    /**
     * ?????????????????????security????????????
     */
    @Override
    public UserDetails loadUserByUserName(String userName){
        //??????????????????
        Admin admin = getAdminInfoByUserName(userName);
        //????????????????????????
        List<Resource> resourceList = getResourceListByUserInfo(admin.getId());
        return new AdminUserDetails(admin,resourceList);
    }

    /**
     * ?????????????????????????????????
     */
    private List<Resource> getResourceListByUserInfo(Long adminId) {
        //??????????????????
        List<Resource> resourceList = adminUserCacheService.getResourceList(adminId);
        if(CollectionUtils.isNotEmpty(resourceList)){
            return resourceList;
        }
        resourceList = adminRoleRelationService.getRosourceListByAdminId(adminId);
        if(CollectionUtils.isNotEmpty(resourceList)){
            //????????????
            adminUserCacheService.setResourceList(adminId,resourceList);
        }
        return resourceList;
    }

    /**
     * ?????????????????????????????????
     */
    @Override
    public AdminInfoDto getAdminUserInfoByUserName(String userName) {
        Admin admin = this.getAdminInfoByUserName(userName);
        //????????????????????????
        Long adminId = admin.getId();
        List<Resource> resourceList = this.getResourceListByUserInfo(adminId);
        //?????????????????????
        List<Role> roleList = this.getAdminRoleInfo(adminId);
        //????????????????????????
        List<Menu> menuList = adminRoleRelationService.getMenuListByAdminId(adminId);
        AdminInfoDto adminInfoDto = new AdminInfoDto();
        adminInfoDto.setAdmin(admin);
        adminInfoDto.setMenuList(menuList);
        adminInfoDto.setRoleList(roleList);
        adminInfoDto.setResourceList(resourceList);
        return adminInfoDto;
    }

    private Admin getAdminInfoByUserName(String userName){
        //??????????????????????????????
        Admin admin = adminUserCacheService.getAdminInfoByRedis(userName);
        if (admin != null) {
            return admin;
        }
        //??????????????????
        admin = new Admin().selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUsername, userName));
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND,admin);
        return admin;
    }

    /**
     * ??????token
     */
    @Override
    public Map<String, Object> refreshToken(String oldToken) {
        HashMap<String, Object> map = new HashMap<>(16);
        String refreshHeadToken = jwtTokenUtil.refreshHeadToken(oldToken);
        ApiAssert.notNull(ErrorCodeEnum.TOKEN_HAS_EXPIRED,refreshHeadToken);
        map.put("token",refreshHeadToken);
        return map;
    }

    /**
     * ??????id????????????????????????
     */
    @Override
    public Admin getUserInfo(Long id) {
        Admin admin = new Admin().selectById(id);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND,admin);
        return admin;
    }

    /**
     * ????????????
     */
    @Override
    public Void logout(String userName) {
        //????????????
        return adminUserCacheService.delAdminToken(userName);
    }

    /**
     * ????????????
     */
    @Override
    public IPage<Admin> getAdminInfoList(String keyword, Integer pageNum, Integer pageSize) {
        IPage<Admin> page = new Page<>(pageNum,pageSize);
        return new Admin().selectPage(page,new QueryWrapper<Admin>().lambda().like(StringUtils.isNotBlank(keyword), Admin::getUsername, keyword).orderByDesc(Admin::getCreateTime));
    }

    /**
     * ??????????????????
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateAdminInfo(Admin admin) {
        Admin info = this.getUserInfo(admin.getId());
        adminUserCacheService.delAdminInfoByRedis(info.getUsername());
        return admin.updateById();
    }

    /**
     * ??????????????????
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
                LOGGER.error("??????????????????:{}",id);
            }
            return flag;
        }).collect(Collectors.toList());
        return true;
    }

    /**
     * ????????????
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updatePassword(AdminPasswordVo adminPasswordVo, String userName) {
        Admin admin = this.getAdminInfoByUserName(userName);
        if(!passwordEncoder.matches(adminPasswordVo.getOldPassword(),admin.getPassword())){
            //????????????
            ApiAssert.failure(ErrorCodeEnum.ORIGINAL_PASSWORD_IS_INCORRECT);
        }
        if(!adminPasswordVo.getNewPassword().equals(adminPasswordVo.getConfirmNewPassword())){
           ApiAssert.failure(ErrorCodeEnum.NEW_PASSWORD_NOT_CONSISTENT);
        }
        admin.setPassword(passwordEncoder.encode(adminPasswordVo.getNewPassword()));
        return admin.updateById();
    }

    /**
     * ?????????????????????
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean distributionAdminRoleRelated(String roleIds, Long adminId) {
        //?????????????????????????????????
        new AdminRoleRelation().delete(Wrappers.<AdminRoleRelation>query().lambda().eq(AdminRoleRelation::getAdminId,adminId));
        Stream.of(roleIds.split(",")).map(Long::parseLong).collect(Collectors.toList()).stream().map(roleId ->{
                AdminRoleRelation adminRoleRelation = new AdminRoleRelation();
                adminRoleRelation.setAdminId(adminId);
                adminRoleRelation.setRoleId(roleId);
                return adminRoleRelation.insert();
        }).collect(Collectors.toList());
        //??????????????????????????????
        adminUserCacheService.delResourceListByAdminId(adminId);
        return true;
    }

    /**
     * ????????????????????????
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
     * ???????????????
     */
    @Override
    public Object createKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        Base64.Encoder encoder = Base64.getEncoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encodeToString(outputStream.toByteArray());
        // ?????????redis???
        redisService.hSet("captcha", key, code, 120);
        //log.info("????????? -- {} - {}", key, code);
        return MapUtil.builder()
                .put("token", key)
                .put("base64Img", base64Img)
                .build();
    }

    @Override
    public UserInfoDto loadUserByUsername(String userName) {
        //??????????????????
        Admin admin = getAdminInfoByUserName(userName);
        //????????????????????????
        List<Role> roleList = this.getAdminRoleInfo(admin.getId());
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(admin,userInfoDto);
        if(CollectionUtils.isNotEmpty(roleList)){
            List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            userInfoDto.setRoles(roleStrList);
        }
        return userInfoDto;
    }

}