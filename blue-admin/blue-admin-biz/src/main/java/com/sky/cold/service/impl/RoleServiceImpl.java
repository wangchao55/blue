package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.RoleDao;
import com.sky.cold.entity.Role;
import com.sky.cold.service.RoleService;
import org.springframework.stereotype.Service;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {



}