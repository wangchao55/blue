package com.sky.cold.cache.service;


import com.sky.cold.entity.Admin;
import com.sky.cold.entity.Resource;

import java.util.List;

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
     * 删除缓存后台用户信息
     */
    void delAdminInfoByRedis(String username);

    /**
     * 缓存token
     */
    void saveAdminToken(String userName, String token);

    /**
     * 删除token
     */
    Void delAdminToken(String userName);

    /**
     * 获取token
     */
    String getAdminToken(String userName);

    /**
     * 获取缓存后台用户资源列表
     */
    List<Resource> getResourceList(Long adminId);

    /**
     * 设置缓存后台用户资源列表
     */
    void setResourceList(Long adminId, List<Resource> resourceList);

    /**
     * 删除后台用户资源列表
     */
    void delResourceListByAdminId(Long adminId);

    /**
     * 当角色相关信息发生改变时删除后台用户资源列表
     */
    void delResourceListByRoleId(Long roleId);

    /**
     * 当资源相关信息发生改变时删除后台用户资源列表
     */
    void delResourceListByResourceId(Long resourceId);
}
