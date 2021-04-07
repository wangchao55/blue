package com.sky.cold.cache.service.impl;

import com.sky.cold.entity.Admin;
import com.sky.cold.cache.service.AdminUserCacheService;
import com.sky.cold.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: wangchao
 * @Date: 2021/4/2 9:38
 */
@Service
public class AdminUserCacheServiceImpl implements AdminUserCacheService {

    @Autowired
    RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public Admin getAdminInfoByRedis(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (Admin) redisService.get(key);
    }

    @Override
    public void saveAdminInfoByRedis(Admin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }
}
