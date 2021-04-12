package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.PermissionDao;
import com.sky.cold.entity.Permission;
import com.sky.cold.service.PermissionService;
import org.springframework.stereotype.Service;


@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {



}