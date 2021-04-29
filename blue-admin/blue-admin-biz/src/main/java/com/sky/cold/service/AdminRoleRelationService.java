package com.sky.cold.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.cold.entity.AdminRoleRelation;
import com.sky.cold.entity.Menu;
import com.sky.cold.entity.Resource;

import java.util.List;


/**
 * 后台用户和角色关系表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
public interface AdminRoleRelationService extends IService<AdminRoleRelation> {

    List<Resource> getRosourceListByAdminId(Long adminId);

    List<Menu> getMenuListByAdminId(Long adminId);
}

