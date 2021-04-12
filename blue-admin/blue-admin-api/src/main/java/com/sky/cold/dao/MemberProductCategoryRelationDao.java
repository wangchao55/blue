package com.sky.cold.dao;

import com.sky.cold.entity.MemberProductCategoryRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员与产品分类关系表（用户喜欢的分类）
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface MemberProductCategoryRelationDao extends BaseMapper<MemberProductCategoryRelation> {
	
}
