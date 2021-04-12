package com.sky.cold.controller;

import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.RoleResourceRelation;
import com.sky.cold.service.RoleResourceRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 后台角色资源关系表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/roleresourcerelation")
@Api(tags = "后台角色资源关系表管理")
public class RoleResourceRelationController extends SuperController {
    @Autowired
    private RoleResourceRelationService roleResourceRelationService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = roleResourceRelationService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		RoleResourceRelation roleResourceRelation = roleResourceRelationService.getById(id);

        return success(roleResourceRelation);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台角色资源关系表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody RoleResourceRelation roleResourceRelation){
		roleResourceRelationService.save(roleResourceRelation);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台角色资源关系表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody RoleResourceRelation roleResourceRelation){
		roleResourceRelationService.updateById(roleResourceRelation);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台角色资源关系表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		roleResourceRelationService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
