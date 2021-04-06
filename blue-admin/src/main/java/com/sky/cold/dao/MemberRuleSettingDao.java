package com.sky.cold.admin.dao;

import com.sky.cold.admin.entity.MemberRuleSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员积分成长规则表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface MemberRuleSettingDao extends BaseMapper<MemberRuleSetting> {
	
}
