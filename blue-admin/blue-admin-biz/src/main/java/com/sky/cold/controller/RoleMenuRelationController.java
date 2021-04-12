package com.sky.cold.controller;

import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.RoleMenuRelation;
import com.sky.cold.service.RoleMenuRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 后台角色菜单关系表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/rolemenurelation")
@Api(tags = "后台角色菜单关系表管理")
public class RoleMenuRelationController extends SuperController {
    @Autowired
    private RoleMenuRelationService roleMenuRelationService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = roleMenuRelationService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		RoleMenuRelation roleMenuRelation = roleMenuRelationService.getById(id);

        return success(roleMenuRelation);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台角色菜单关系表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody RoleMenuRelation roleMenuRelation){
		roleMenuRelationService.save(roleMenuRelation);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台角色菜单关系表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody RoleMenuRelation roleMenuRelation){
		roleMenuRelationService.updateById(roleMenuRelation);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台角色菜单关系表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		roleMenuRelationService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
