package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.cache.service.AdminUserCacheService;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.dao.ResourceDao;
import com.sky.cold.entity.Resource;
import com.sky.cold.entity.ResourceCategory;
import com.sky.cold.security.component.DynamicSecurityMetadataSource;
import com.sky.cold.security.component.DynamicSecurityService;
import com.sky.cold.service.ResourceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service("resourceService")
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {

    @Autowired
    DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @Autowired
    AdminUserCacheService adminUserCacheService;


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
        //清空资源规则列表
        dynamicSecurityMetadataSource.clearDataSource();
        resource.setCreateTime(new Date());
        return resource.insert();
    }

    /**
     * 修改资源
     */
    @Override
    public Boolean updateResourceInfo(Resource resource) {
        dynamicSecurityMetadataSource.clearDataSource();
        adminUserCacheService.delResourceListByResourceId(resource.getId());
        return resource.updateById();
    }


    /**
     * 删除资源
     */
    @Override
    public Boolean deleteResourceInfo(Long id) {
        dynamicSecurityMetadataSource.clearDataSource();
        adminUserCacheService.delResourceListByResourceId(id);
        return new Resource().deleteById(id);
    }

    /**
     * 获取资源列表
     */
    @Override
    public List<Resource> getResourceListAll() {
        return new Resource().selectList(Wrappers.<Resource>query().lambda());
    }


}