package com.sky.cold.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.common.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.admin.entity.Permission;
import com.sky.cold.admin.service.PermissionService;



/**
 * 后台用户权限表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/permission")
@Api(tags = "后台用户权限表管理")
public class PermissionController extends SuperController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = permissionService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		Permission permission = permissionService.getById(id);

        return success(permission);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台用户权限表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody Permission permission){
		permissionService.save(permission);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台用户权限表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody Permission permission){
		permissionService.updateById(permission);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台用户权限表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		permissionService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
