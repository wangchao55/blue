package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.ResourceCategoryDao;
import com.sky.cold.entity.ResourceCategory;
import com.sky.cold.service.ResourceCategoryService;
import org.springframework.stereotype.Service;


@Service("resourceCategoryService")
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryDao, ResourceCategory> implements ResourceCategoryService {



}