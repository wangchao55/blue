package com.sky.cold.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.rest.responses.SuccessResponses;
import com.sky.cold.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.admin.entity.Role;
import com.sky.cold.admin.service.RoleService;



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
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = roleService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		Role role = roleService.getById(id);

        return success(role);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台用户角色表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody Role role){
		roleService.save(role);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台用户角色表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody Role role){
		roleService.updateById(role);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台用户角色表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		roleService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
