package com.sky.cold.admin.dao;

import com.sky.cold.admin.entity.MemberLevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员等级表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface MemberLevelDao extends BaseMapper<MemberLevel> {
	
}
