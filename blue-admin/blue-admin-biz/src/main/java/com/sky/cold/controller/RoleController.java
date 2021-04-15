package com.sky.cold.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.Role;
import com.sky.cold.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 后台用户角色表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/role")
@Api(tags = "后台用户角色表管理")
public class RoleController extends SuperController {
    @Autowired
    private RoleService roleService;

    /**
     * 列表查询
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/getRoleList/{pageNum}/{pageSize}/{name}")
    public SuccessResponses<IPage<Role>> getRoleList(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize,@PathVariable("name") String name){
        return success(roleService.getRoleList(pageNum,pageSize,name));
    }

    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/getRoleInfo/{id}")
    public SuccessResponses<Role> getRoleInfo(@PathVariable("id") Long id){
        return success(roleService.getRoleInfo(id));
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台用户角色表")
    @PostMapping("/saveRoleInfo")
    public SuccessResponses<Boolean> saveRoleInfo(@RequestBody Role role){
        return success(roleService.saveRoleInfo(role));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台用户角色表")
    @PutMapping("/updateRoleInfo")
    public SuccessResponses<Boolean> updateRoleInfo(@RequestBody Role role){
        return success(roleService.updateRoleInfo(role));
    }

    /**
     * 修改角色状态
     */
    @ApiOperation(value = "修改角色状态")
    @PutMapping("/updateRoleStatus")
    public SuccessResponses<Boolean> updateRoleStatus(String ids){
        return success(roleService.updateRoleStatus(ids));
    }

}
