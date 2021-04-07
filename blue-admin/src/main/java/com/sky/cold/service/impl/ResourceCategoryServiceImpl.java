package com.sky.cold.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.ResourceCategoryDao;
import com.sky.cold.entity.ResourceCategory;
import com.sky.cold.service.ResourceCategoryService;


@Service("resourceCategoryService")
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryDao, ResourceCategory> implements ResourceCategoryService {



}