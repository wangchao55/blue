package com.sky.cold.dto;

import com.sky.cold.entity.Admin;
import com.sky.cold.entity.Menu;
import com.sky.cold.entity.Resource;
import com.sky.cold.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * @author wangchao
 * @date 2021-04-29 3:00 下午
 */
@Data
public class AdminInfoDto {
    private Admin admin;
    private List<Resource> resourceList;
    private List<Menu> menuList;
    private List<Role> roleList;
}
