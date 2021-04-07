package com.sky.cold.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.cold.entity.Admin;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;


/**
 * 后台用户表
 *
 * @author wangchao
 * @date 2021-03-30 16:54:44
 */
public interface AdminService extends IService<Admin> {

    /**
     * 注册
     */
    boolean register(Admin admin);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUserName(String username);

    Map<String,Object> login(Admin admin);

    /**
     * 通过用户名称获取用户信息
     */
    Admin getAdminUserInfoByUserName(String userName);

    Map<String,Object> refreshToken(String token);

    /**
     * 通过id获取用户名称
     */
    Admin getUserInfo(Long id);
}

