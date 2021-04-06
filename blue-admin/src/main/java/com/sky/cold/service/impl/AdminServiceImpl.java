package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.admin.bo.AdminUserDetails;
import com.sky.cold.admin.dao.AdminDao;
import com.sky.cold.admin.entity.Admin;
import com.sky.cold.admin.service.AdminService;
import com.sky.cold.cache.service.AdminUserCacheService;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.security.util.JWTTokenUtil;
import com.sky.cold.common.util.ApiAssert;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
    public boolean register(Admin admin) {
        //查询该用户名是否已注册
        Admin info = new Admin().selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUsername, admin.getUsername()));
        ApiAssert.isNull(ErrorCodeEnum.USERNAME_ALREADY_EXISTS,info);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setCreateTime(new Date());
        admin.setStatus(1);
        return admin.insert();
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
            //生成token
            token = jwtTokenUtil.generateToken(userDetails);
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
        if (admin != null) return admin;
        //查询用户信息
        admin = new Admin().selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUsername, userName));
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND,admin);
        //用户信息存入缓存
        adminUserCacheService.saveAdminInfoByRedis(admin);
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
}