package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MemberTaskDao;
import com.sky.cold.entity.MemberTask;
import com.sky.cold.service.MemberTaskService;
import org.springframework.stereotype.Service;


@Service("memberTaskService")
public class MemberTaskServiceImpl extends ServiceImpl<MemberTaskDao, MemberTask> implements MemberTaskService {



}