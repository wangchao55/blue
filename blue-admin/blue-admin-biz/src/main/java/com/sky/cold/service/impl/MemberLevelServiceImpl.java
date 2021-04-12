package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MemberLevelDao;
import com.sky.cold.entity.MemberLevel;
import com.sky.cold.service.MemberLevelService;
import org.springframework.stereotype.Service;


@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelDao, MemberLevel> implements MemberLevelService {



}