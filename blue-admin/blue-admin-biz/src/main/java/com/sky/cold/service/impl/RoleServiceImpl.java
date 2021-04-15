package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.dao.RoleDao;
import com.sky.cold.entity.Role;
import com.sky.cold.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    /**
     * 新增角色信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveRoleInfo(Role role) {
        getRoleInfoByName(role.getName());
        role.setCreateTime(new Date());
        return role.insert();
    }

    public Role getRoleInfoByName(String name){
        //查询该角色是否已创建
        Role info = new Role().selectOne(Wrappers.<Role>query().lambda().eq(Role::getName, name));
        ApiAssert.isNull(ErrorCodeEnum.ROLE_ALREADY_EXISTS,info);
        return info;
    }

    /**
     * 修改角色信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateRoleInfo(Role role) {
        getRoleInfoByName(role.getName());
        return role.updateById();
    }

    /**
     * 获取角色详情
     */
    @Override
    public Role getRoleInfo(Long id) {
        Role role = new Role().selectById(id);
        ApiAssert.notNull(ErrorCodeEnum.ROLE_NOT_FOUND,role);
        return role;
    }

    /**
     * 列表查询
     */
    @Override
    public IPage<Role> getRoleList(int pageNum, int pageSize, String name) {
        IPage<Role> page = new Page<>(pageNum, pageSize);
        return new Role().selectPage(page, Wrappers.<Role>query().lambda().like(StringUtils.isNotBlank(name), Role::getName, name).orderByDesc(Role::getCreateTime));
    }

    /**
     * 修改角色状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateRoleStatus(String ids) {
        Stream.of(ids.split(",")).map(Long::parseLong).collect(Collectors.toList()).stream().map(id -> {
            boolean flag = false;
            try {
                Role role = new Role().selectById(id);
                ApiAssert.notNull(ErrorCodeEnum.ROLE_NOT_FOUND,role);
                role.setStatus(role.getStatus() == 1 ? 0 : 1);
                flag = role.updateById();
            } catch (Exception e) {
                LOGGER.error("修改角色失败:{},错误日志:{}",id,e.getMessage());
            }
            return flag;
        }).collect(Collectors.toList());
        return true;
    }

}