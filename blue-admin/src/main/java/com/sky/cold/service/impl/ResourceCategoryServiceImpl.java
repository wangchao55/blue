package com.sky.cold.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.admin.dao.ResourceCategoryDao;
import com.sky.cold.admin.entity.ResourceCategory;
import com.sky.cold.admin.service.ResourceCategoryService;


@Service("resourceCategoryService")
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryDao, ResourceCategory> implements ResourceCategoryService {



}