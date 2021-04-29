package com.sky.cold.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.cold.entity.Menu;
import com.sky.cold.entity.Resource;
import com.sky.cold.entity.Role;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * 后台用户角色表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
public interface RoleService extends IService<Role> {

    Boolean saveRoleInfo(Role role);

    Boolean updateRoleInfo(Role role);

    Role getRoleInfo(Long id);

    IPage<Role> getRoleList(int pageNum, int pageSize, String name);

    Boolean updateRoleStatus(String ids);

    Boolean saveMenuToRole(String menuIds, Long roleId);

    Boolean saveResourceToRole(String resourceIds, Long roleId);

    List<Menu> getMenuList(Long roleId);

    List<Resource> getResourceListByRoleId(Long roleId);

    Boolean deleteRole(String ids);
}

