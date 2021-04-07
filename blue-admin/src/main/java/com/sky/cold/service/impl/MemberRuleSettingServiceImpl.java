package com.sky.cold.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MemberRuleSettingDao;
import com.sky.cold.entity.MemberRuleSetting;
import com.sky.cold.service.MemberRuleSettingService;


@Service("memberRuleSettingService")
public class MemberRuleSettingServiceImpl extends ServiceImpl<MemberRuleSettingDao, MemberRuleSetting> implements MemberRuleSettingService {



}