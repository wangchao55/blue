package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.IntegrationChangeHistoryDao;
import com.sky.cold.entity.IntegrationChangeHistory;
import com.sky.cold.service.IntegrationChangeHistoryService;
import org.springframework.stereotype.Service;


@Service("integrationChangeHistoryService")
public class IntegrationChangeHistoryServiceImpl extends ServiceImpl<IntegrationChangeHistoryDao, IntegrationChangeHistory> implements IntegrationChangeHistoryService {



}