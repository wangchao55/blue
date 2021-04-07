package com.sky.cold.dao;

import com.sky.cold.entity.AdminPermissionRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface AdminPermissionRelationDao extends BaseMapper<AdminPermissionRelation> {
	
}
