package com.sky.cold.admin.dao;

import com.sky.cold.admin.entity.RolePermissionRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户角色和权限关系表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface RolePermissionRelationDao extends BaseMapper<RolePermissionRelation> {
	
}
