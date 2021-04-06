package com.sky.cold.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.admin.dao.RoleDao;
import com.sky.cold.admin.entity.Role;
import com.sky.cold.admin.service.RoleService;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {



}