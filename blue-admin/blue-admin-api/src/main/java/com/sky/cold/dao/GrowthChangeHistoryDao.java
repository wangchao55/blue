package com.sky.cold.dao;

import com.sky.cold.entity.GrowthChangeHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 成长值变化历史记录表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface GrowthChangeHistoryDao extends BaseMapper<GrowthChangeHistory> {
	
}
