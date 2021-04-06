package com.sky.cold.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.admin.dao.MemberLoginLogDao;
import com.sky.cold.admin.entity.MemberLoginLog;
import com.sky.cold.admin.service.MemberLoginLogService;


@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogDao, MemberLoginLog> implements MemberLoginLogService {



}