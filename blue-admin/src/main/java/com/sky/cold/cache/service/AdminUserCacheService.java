package com.sky.cold.cache.service;

import com.sky.cold.entity.Admin;

/**
 * @Author: wangchao
 * @Date: 2021/4/2 9:38
 */
public interface AdminUserCacheService {

    /**
     * 获取缓存后台用户信息
     */
    Admin getAdminInfoByRedis(String username);

    /**
     * 设置缓存后台用户信息
     */
    void saveAdminInfoByRedis(Admin admin);

    /**
     * 缓存token
     */
    void saveAdminToken(String userName,String token);

    /**
     * 删除token
     */
    Void delAdminToken(String userName);

    /**
     * 获取token
     */
    String getAdminToken(String userName);
}
