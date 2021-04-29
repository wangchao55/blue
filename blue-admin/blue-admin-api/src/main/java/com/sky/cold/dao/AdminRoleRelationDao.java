package com.sky.cold.dao;

import com.sky.cold.entity.AdminRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.cold.entity.Menu;
import com.sky.cold.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户和角色关系表
 * 
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Mapper
public interface AdminRoleRelationDao extends BaseMapper<AdminRoleRelation> {

    List<Resource> getRosourceListByAdminId(@Param("adminId") Long adminId);

    List<Menu> getMenuListByAdminId(@Param("adminId") Long adminId);

}
