package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.ResourceDao;
import com.sky.cold.entity.Resource;
import com.sky.cold.service.ResourceService;
import org.springframework.stereotype.Service;


@Service("resourceService")
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {



}