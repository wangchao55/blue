package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.cache.service.AdminUserCacheService;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.dao.RoleDao;
import com.sky.cold.entity.*;
import com.sky.cold.service.ResourceService;
import com.sky.cold.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);


    @Autowired
    AdminUserCacheService adminUserCacheService;

    @Autowired
    ResourceService resourceService;

    /**
     * 新增角色信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveRoleInfo(Role role) {
        checkRoleIsExist(role.getName());
        boolean flag = role.insert();
        return flag;
    }

    public void checkRoleIsExist(String name){
        //查询该角色是否已创建
        Role info = new Role().selectOne(Wrappers.<Role>query().lambda().eq(Role::getName, name));
        ApiAssert.isNull(ErrorCodeEnum.ROLE_ALREADY_EXISTS,info);
    }

    /**
     * 修改角色信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateRoleInfo(Role role) {
        checkRoleIsExist(role.getName());
        boolean flag = role.updateById();
        return flag;
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
        Stream.of(ids.split(",")).map(Long::parseLong).collect(Collectors.toList()).forEach(id -> {
            try {
                Role role = new Role().selectById(id);
                ApiAssert.notNull(ErrorCodeEnum.ROLE_NOT_FOUND,role);
                role.setStatus(role.getStatus() == 1 ? 0 : 1);
                role.updateById();
            } catch (Exception e) {
                LOGGER.error("修改角色失败:{},错误日志:{}",id,e.getMessage());
            }
        });
        return true;
    }

    /**
     * 给角色分配菜单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveMenuToRole(String menuIds, Long roleId) {
        //删除之前角色所属菜单
        new RoleMenuRelation().delete(Wrappers.<RoleMenuRelation>query().lambda().eq(RoleMenuRelation::getRoleId,roleId));
        Stream.of(menuIds.split(",")).map(Long::parseLong).collect(Collectors.toList()).forEach(menuId -> {
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
            roleMenuRelation.setRoleId(roleId);
            roleMenuRelation.setMenuId(menuId);
            roleMenuRelation.insert();
        });
        return true;
    }

    /**
     * 给角色分配资源
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveResourceToRole(String resourceIds, Long roleId) {
        new RoleResourceRelation().delete(Wrappers.<RoleResourceRelation>query().lambda().eq(RoleResourceRelation::getRoleId,roleId));
        Stream.of(resourceIds.split(",")).map(Long::parseLong).collect(Collectors.toList()).forEach(resourceId -> {
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelation.setRoleId(roleId);
            roleResourceRelation.insert();
        });
        adminUserCacheService.delResourceListByRoleId(roleId);
        resourceService.initResourceRolesMap();
        return true;
    }

    /**
     * 获取角色下的菜单
     */
    @Override
    public List<Menu> getMenuList(Long roleId) {
        List<RoleMenuRelation> roleMenuRelationList = new RoleMenuRelation().selectList(Wrappers.<RoleMenuRelation>query().lambda()
                .select(RoleMenuRelation::getMenuId)
                .eq(RoleMenuRelation::getRoleId, roleId));
        return roleMenuRelationList
                .stream().map(RoleMenuRelation::getMenuId).collect(Collectors.toList())
                .stream().map(menuId -> new Menu().selectById(menuId)).collect(Collectors.toList());
    }

    /**
     * 获取角色下的资源
     */
    @Override
    public List<Resource> getResourceListByRoleId(Long roleId) {
        List<RoleResourceRelation> roleResourceRelationList = new RoleResourceRelation().selectList(Wrappers.<RoleResourceRelation>query().lambda()
                .select(RoleResourceRelation::getResourceId)
                .eq(RoleResourceRelation::getRoleId, roleId));
        return roleResourceRelationList.stream().map(roleResourceRelation -> new Resource().selectById(roleResourceRelation)).collect(Collectors.toList());
    }

    /**
     * 批量删除角色信息
     * @param ids 角色id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRole(String ids) {
        Stream.of(ids.split(",")).map(Long::parseLong).collect(Collectors.toList()).forEach(roleId -> {
                    adminUserCacheService.delResourceListByRoleId(roleId);
                    this.removeById(roleId);
        });
        resourceService.initResourceRolesMap();
        return true;
    }

}