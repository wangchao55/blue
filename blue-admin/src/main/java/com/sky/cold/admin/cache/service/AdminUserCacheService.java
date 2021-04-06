package com.sky.cold.admin.cache.service;

import com.sky.cold.admin.entity.Admin;

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
}
