package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.bo.AdminUserDetails;
import com.sky.cold.cache.service.AdminUserCacheService;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.dao.AdminDao;
import com.sky.cold.entity.Admin;
import com.sky.cold.entity.AdminRoleRelation;
import com.sky.cold.security.util.JWTTokenUtil;
import com.sky.cold.service.AdminService;
import com.sky.cold.vo.AdminPasswordVo;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    AdminUserCacheService adminUserCacheService;

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
        Admin admin = getAdminUserInfoByUserName(userName);
        return new AdminUserDetails(admin);
    }

    /**
     * 通过用户名获取用户信息
     */
    @Override
    public Admin getAdminUserInfoByUserName(String userName) {
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
        Admin admin = this.getAdminUserInfoByUserName(userName);
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
        return true;
    }

}