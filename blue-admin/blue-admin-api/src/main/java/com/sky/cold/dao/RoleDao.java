package com.sky.cold.dao;

import com.sky.cold.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户角色表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {
	
}
