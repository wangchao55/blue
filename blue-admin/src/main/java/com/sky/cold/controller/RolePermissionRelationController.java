package com.sky.cold.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.common.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.entity.RolePermissionRelation;
import com.sky.cold.service.RolePermissionRelationService;



/**
 * 后台用户角色和权限关系表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/rolepermissionrelation")
@Api(tags = "后台用户角色和权限关系表管理")
public class RolePermissionRelationController extends SuperController {
    @Autowired
    private RolePermissionRelationService rolePermissionRelationService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = rolePermissionRelationService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		RolePermissionRelation rolePermissionRelation = rolePermissionRelationService.getById(id);

        return success(rolePermissionRelation);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台用户角色和权限关系表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody RolePermissionRelation rolePermissionRelation){
		rolePermissionRelationService.save(rolePermissionRelation);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台用户角色和权限关系表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody RolePermissionRelation rolePermissionRelation){
		rolePermissionRelationService.updateById(rolePermissionRelation);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台用户角色和权限关系表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		rolePermissionRelationService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
