package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MemberLoginLogDao;
import com.sky.cold.entity.MemberLoginLog;
import com.sky.cold.service.MemberLoginLogService;
import org.springframework.stereotype.Service;


@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogDao, MemberLoginLog> implements MemberLoginLogService {



}