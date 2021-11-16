package com.sky.cold.cache.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sky.cold.cache.service.AdminUserCacheService;
import com.sky.cold.common.cache.cms.CmsCacheKey;
import com.sky.cold.common.service.RedisService;
import com.sky.cold.entity.Admin;
import com.sky.cold.entity.AdminRoleRelation;
import com.sky.cold.entity.Resource;
import com.sky.cold.entity.RoleResourceRelation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Author: wangchao
 * @date: 2021/4/2 9:38
 */
@Service
@RequiredArgsConstructor
public class AdminUserCacheServiceImpl implements AdminUserCacheService {

    private final RedisService redisService;

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
     * 删除后台账户信息
     */
    @Override
    public void delAdminInfoByRedis(String username) {
        redisService.del(MessageFormat.format(CmsCacheKey.adminInfo,username));
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
    public Void delAdminToken(String userName) {
        redisService.del(MessageFormat.format(CmsCacheKey.adminToken,userName));
        return null;
    }

    /**
     * 获取后台缓存
     */
    @Override
    public String getAdminToken(String userName) {
        return (String) redisService.get(MessageFormat.format(CmsCacheKey.adminToken,userName));
    }

    /**
     * 获取该用户下资源列表
     */
    @Override
    public List<Resource> getResourceList(Long adminId) {
        return (List<Resource>) redisService.get(MessageFormat.format(CmsCacheKey.adminResource,adminId));
    }

    /**
     * 保存该用户下资源列表
     */
    @Override
    public void setResourceList(Long adminId, List<Resource> resourceList) {
        redisService.set(MessageFormat.format(CmsCacheKey.adminResource,adminId), resourceList, REDIS_EXPIRE);
    }

    /**
     * 清除该用户下资源列表
     */
    @Override
    public void delResourceListByAdminId(Long adminId) {
        redisService.del(MessageFormat.format(CmsCacheKey.adminResource,adminId));
    }

    /**
     * 当角色相关信息改变时清除该用户下资源列表
     */
    @Override
    public void delResourceListByRoleId(Long roleId) {
        List<AdminRoleRelation> adminRoleRelationList = new AdminRoleRelation().selectList(Wrappers.<AdminRoleRelation>query().lambda().select(AdminRoleRelation::getAdminId).eq(AdminRoleRelation::getRoleId, roleId));
        adminRoleRelationList.forEach(adminRoleRelation -> this.delResourceListByAdminId(adminRoleRelation.getAdminId()));

    }

    /**
     * 当资源相关信息改变时清除该用户下资源列表
     * @param resourceId 资源id
     */
    @Override
    public void delResourceListByResourceId(Long resourceId) {
        List<RoleResourceRelation> roleResourceRelationList = new RoleResourceRelation().selectList(Wrappers.<RoleResourceRelation>query().lambda().select(RoleResourceRelation::getRoleId));
        roleResourceRelationList.forEach(roleResourceRelation -> this.delResourceListByRoleId(roleResourceRelation.getRoleId()));
    }
}
