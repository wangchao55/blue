package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.AdminRoleRelationDao;
import com.sky.cold.dao.RoleResourceRelationDao;
import com.sky.cold.entity.AdminRoleRelation;
import com.sky.cold.entity.Menu;
import com.sky.cold.entity.Resource;
import com.sky.cold.service.AdminRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("adminRoleRelationService")
public class AdminRoleRelationServiceImpl extends ServiceImpl<AdminRoleRelationDao, AdminRoleRelation> implements AdminRoleRelationService {

    @Autowired
    AdminRoleRelationDao adminRoleRelationDao;


    /**
     * 获取该用户下所有资源列表
     */
    @Override
    public List<Resource> getRosourceListByAdminId(Long adminId) {
        return adminRoleRelationDao.getRosourceListByAdminId(adminId);
    }

    /**
     * 获取该用户下所有菜单列表
     */
    @Override
    public List<Menu> getMenuListByAdminId(Long adminId) {
        return adminRoleRelationDao.getMenuListByAdminId(adminId);
    }
}