package com.sky.cold.dao;

import com.sky.cold.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface MemberDao extends BaseMapper<Member> {
	
}
