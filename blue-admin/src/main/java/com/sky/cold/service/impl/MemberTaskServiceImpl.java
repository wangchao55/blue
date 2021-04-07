package com.sky.cold.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MemberTaskDao;
import com.sky.cold.entity.MemberTask;
import com.sky.cold.service.MemberTaskService;


@Service("memberTaskService")
public class MemberTaskServiceImpl extends ServiceImpl<MemberTaskDao, MemberTask> implements MemberTaskService {



}