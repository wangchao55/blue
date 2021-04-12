package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.GrowthChangeHistoryDao;
import com.sky.cold.entity.GrowthChangeHistory;
import com.sky.cold.service.GrowthChangeHistoryService;
import org.springframework.stereotype.Service;


@Service("growthChangeHistoryService")
public class GrowthChangeHistoryServiceImpl extends ServiceImpl<GrowthChangeHistoryDao, GrowthChangeHistory> implements GrowthChangeHistoryService {



}