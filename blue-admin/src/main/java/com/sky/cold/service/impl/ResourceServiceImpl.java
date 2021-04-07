package com.sky.cold.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.ResourceDao;
import com.sky.cold.entity.Resource;
import com.sky.cold.service.ResourceService;


@Service("resourceService")
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {



}