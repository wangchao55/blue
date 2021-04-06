package com.sky.cold.admin.dao;

import com.sky.cold.admin.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台资源表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface ResourceDao extends BaseMapper<Resource> {
	
}
