package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.dao.ResourceCategoryDao;
import com.sky.cold.entity.ResourceCategory;
import com.sky.cold.service.ResourceCategoryService;
import org.springframework.stereotype.Service;


@Service("resourceCategoryService")
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryDao, ResourceCategory> implements ResourceCategoryService {


    /**
     * 分页查询列表
     */
    @Override
    public IPage<ResourceCategory> getResourceCategoryList(Integer pageNum, Integer pageSize) {
        IPage<ResourceCategory> page = new Page(pageNum, pageSize);
        return new ResourceCategory().selectPage(page, Wrappers.<ResourceCategory>query().lambda().orderByDesc(ResourceCategory::getId));
    }

    /**
     * 获取详情
     */
    @Override
    public ResourceCategory getResourceCategoryInfo(Long id) {
        ResourceCategory resourceCategory = new ResourceCategory().selectById(id);
        ApiAssert.notNull(ErrorCodeEnum.RESOURCE_CATEGPRY_NOT_FOUND,resourceCategory);
        return resourceCategory;
    }

    /**
     * 新增资源分类
     */
    @Override
    public Boolean saveResourceCategoryInfo(ResourceCategory resourceCategory) {
        ResourceCategory info = new ResourceCategory().selectOne(Wrappers.<ResourceCategory>query().lambda().eq(ResourceCategory::getName, resourceCategory.getName()));
        ApiAssert.notNull(ErrorCodeEnum.RESOURCE_CATEGORY_ALREADY_EXISTS,info);
        return resourceCategory.insert();
    }

    /**
     * 修改资源分类
     */
    @Override
    public Boolean updateResourceCategoryInfo(ResourceCategory resourceCategory) {
        return resourceCategory.updateById();
    }

    /**
     * 删除资源分类
     */
    @Override
    public Boolean delete(Long id) {
        return new ResourceCategory().deleteById(id);
    }
}