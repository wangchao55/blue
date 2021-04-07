package com.sky.cold.dao;

import com.sky.cold.entity.AdminLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户登录日志表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface AdminLoginLogDao extends BaseMapper<com.sky.cold.entity.AdminLoginLog> {
	
}
