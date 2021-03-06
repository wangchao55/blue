package com.sky.cold.dao;

import com.sky.cold.entity.RoleMenuRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台角色菜单关系表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface RoleMenuRelationDao extends BaseMapper<RoleMenuRelation> {
	
}
