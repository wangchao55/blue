package com.sky.cold.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.cold.entity.ResourceCategory;
import org.springframework.http.HttpStatus;


/**
 * 资源分类表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
public interface ResourceCategoryService extends IService<ResourceCategory> {

    IPage<ResourceCategory> getResourceCategoryList(Integer pageNum, Integer pageSize);

    ResourceCategory getResourceCategoryInfo(Long id);

    Boolean saveResourceCategoryInfo(ResourceCategory resourceCategory);

    Boolean updateResourceCategoryInfo(ResourceCategory resourceCategory);

    Boolean delete(Long id);
}

