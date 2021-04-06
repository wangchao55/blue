package com.sky.cold.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.admin.dao.PermissionDao;
import com.sky.cold.admin.entity.Permission;
import com.sky.cold.admin.service.PermissionService;


@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {



}