package com.sky.cold.dao;

import com.sky.cold.entity.MemberTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户标签表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface MemberTagDao extends BaseMapper<MemberTag> {
	
}
