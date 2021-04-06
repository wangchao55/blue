package com.sky.cold.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.admin.dao.MemberTaskDao;
import com.sky.cold.admin.entity.MemberTask;
import com.sky.cold.admin.service.MemberTaskService;


@Service("memberTaskService")
public class MemberTaskServiceImpl extends ServiceImpl<MemberTaskDao, MemberTask> implements MemberTaskService {



}