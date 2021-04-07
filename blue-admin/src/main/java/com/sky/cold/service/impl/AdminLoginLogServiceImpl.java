package com.sky.cold.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.AdminLoginLogDao;
import com.sky.cold.entity.AdminLoginLog;
import com.sky.cold.service.AdminLoginLogService;


@Service("adminLoginLogService")
public class AdminLoginLogServiceImpl extends ServiceImpl<AdminLoginLogDao, AdminLoginLog> implements AdminLoginLogService {



}