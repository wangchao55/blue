package com.sky.cold.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.cold.entity.Role;
import org.springframework.http.HttpStatus;


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
}

