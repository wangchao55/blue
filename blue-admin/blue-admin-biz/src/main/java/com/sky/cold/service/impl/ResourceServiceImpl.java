package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.cache.service.AdminUserCacheService;
import com.sky.cold.common.constant.AuthConstant;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.service.RedisService;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.dao.ResourceDao;
import com.sky.cold.entity.Resource;
import com.sky.cold.entity.Role;
import com.sky.cold.entity.RoleResourceRelation;
import com.sky.cold.service.ResourceService;
import com.sky.cold.service.RoleResourceRelationService;
import com.sky.cold.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service("resourceService")
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {

    @Autowired
    AdminUserCacheService adminUserCacheService;
    @Autowired
    RoleService roleService;
    @Autowired
    RoleResourceRelationService roleResourceRelationService;
    @Autowired
    RedisService redisService;


    /**
     * 获取资源列表
     */
    @Override
    public IPage<Resource> getResourceList(Integer pageNum, Integer pageSize, Long categoryId, String resourceName, String resourceUrl) {
        IPage<Resource> page = new Page(pageNum,pageSize);
        return new Resource().selectPage(page, Wrappers.<Resource>query().lambda()
                .eq(categoryId != null,Resource::getCategoryId,categoryId)
                .like(StringUtils.isNotBlank(resourceName),Resource::getName,resourceName)
                .like(StringUtils.isNotBlank(resourceUrl),Resource::getUrl,resourceUrl));
    }

    /**
     * 获取详情
     */
    @Override
    public Resource getResourceInfo(Long id) {
        Resource resource = new Resource().selectById(id);
        ApiAssert.notNull(ErrorCodeEnum.RESOURCE_NOT_FOUND,resource);
        return resource;
    }

    /**
     * 新增资源
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveResourceInfo(Resource resource) {
        Resource info = new Resource().selectOne(Wrappers.<Resource>query().lambda().eq(Resource::getName, resource.getName()));
        ApiAssert.isNull(ErrorCodeEnum.RESOURCE_ALREADY_EXISTS,info);
        resource.setCreateTime(new Date());
        boolean flag = resource.insert();
        initResourceRolesMap();
        return flag;
    }

    /**
     * 修改资源
     */
    @Override
    public Boolean updateResourceInfo(Resource resource) {
        adminUserCacheService.delResourceListByResourceId(resource.getId());
        boolean flag = resource.updateById();
        initResourceRolesMap();
        return flag;
    }


    /**
     * 删除资源
     */
    @Override
    public Boolean deleteResourceInfo(Long id) {
        adminUserCacheService.delResourceListByResourceId(id);
        boolean flag = this.removeById(id);
        initResourceRolesMap();
        return flag;
    }

    /**
     * 获取资源列表
     */
    @Override
    public List<Resource> getResourceListAll() {
        return new Resource().selectList(Wrappers.<Resource>query().lambda());
    }

    /**
     * 初始化资源角色关联
     */
    @Override
    public void initResourceRolesMap() {
        Map<String, List<String>> resourceRoleMap = new TreeMap<>();
        List<Resource> resourceList = this.list();
        List<Role> roleList = roleService.list();
        List<RoleResourceRelation> roleResourceRelationList = roleResourceRelationService.list();
        for (Resource resource : resourceList) {
            Set<Long> roleIds = roleResourceRelationList.stream().filter(item -> item.getResourceId().equals(resource.getId())).map(RoleResourceRelation::getRoleId).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            resourceRoleMap.put(resource.getUrl(),roleNames);
        }
        redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRoleMap);
        System.out.println(resourceRoleMap);
    }


}