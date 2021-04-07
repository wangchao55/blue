package com.sky.cold.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.common.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.entity.AdminRoleRelation;
import com.sky.cold.service.AdminRoleRelationService;



/**
 * 后台用户和角色关系表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/adminrolerelation")
@Api(tags = "后台用户和角色关系表管理")
public class AdminRoleRelationController extends SuperController {
    @Autowired
    private AdminRoleRelationService adminRoleRelationService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = adminRoleRelationService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		AdminRoleRelation adminRoleRelation = adminRoleRelationService.getById(id);

        return success(adminRoleRelation);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台用户和角色关系表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody AdminRoleRelation adminRoleRelation){
		adminRoleRelationService.save(adminRoleRelation);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台用户和角色关系表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody AdminRoleRelation adminRoleRelation){
		adminRoleRelationService.updateById(adminRoleRelation);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台用户和角色关系表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		adminRoleRelationService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
