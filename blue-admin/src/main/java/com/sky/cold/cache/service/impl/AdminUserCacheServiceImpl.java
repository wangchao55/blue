package com.sky.cold.cache.service.impl;

import com.sky.cold.common.cache.cms.CmsCacheKey;
import com.sky.cold.entity.Admin;
import com.sky.cold.cache.service.AdminUserCacheService;
import com.sky.cold.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * @Author: wangchao
 * @Date: 2021/4/2 9:38
 */
@Service
public class AdminUserCacheServiceImpl implements AdminUserCacheService {

    @Autowired
    RedisService redisService;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    /**
     * 获取后台账户信息
     */
    @Override
    public Admin getAdminInfoByRedis(String username) {
        return (Admin) redisService.get(MessageFormat.format(CmsCacheKey.adminInfo,username));
    }

    /**
     * 保存后台账户信息
     */
    @Override
    public void saveAdminInfoByRedis(Admin admin) {
        redisService.set(MessageFormat.format(CmsCacheKey.adminInfo,admin.getUsername()), admin, REDIS_EXPIRE);
    }

    /**
     * 保存后台token
     */
    @Override
    public void saveAdminToken(String userName,String token) {
        redisService.set(MessageFormat.format(CmsCacheKey.adminToken,userName),token,REDIS_EXPIRE);
    }

    /**
     * 删除后台token
     */
    @Override
    public void delAdminToken(String userName) {
        redisService.del(MessageFormat.format(CmsCacheKey.adminToken,userName));
    }

    /**
     * 获取后台缓存
     */
    @Override
    public String getAdminToken(String userName) {
        return (String) redisService.get(MessageFormat.format(CmsCacheKey.adminToken,userName));
    }
}
