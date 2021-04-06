package com.sky.cold.admin.dao;

import com.sky.cold.admin.entity.MemberMemberTagRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和标签关系表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface MemberMemberTagRelationDao extends BaseMapper<MemberMemberTagRelation> {
	
}
