package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.dao.ResourceCategoryDao;
import com.sky.cold.dao.RoleResourceRelationDao;
import com.sky.cold.entity.ResourceCategory;
import com.sky.cold.entity.RoleResourceRelation;
import com.sky.cold.service.ResourceCategoryService;
import com.sky.cold.service.RoleResourceRelationService;
import org.springframework.stereotype.Service;


/**
 * @author wangchao
 */
@Service
public class RoleResourceRelationServiceImpl extends ServiceImpl<RoleResourceRelationDao, RoleResourceRelation> implements RoleResourceRelationService {

}