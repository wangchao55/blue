package com.sky.cold.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.rest.responses.SuccessResponses;
import com.sky.cold.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.admin.entity.AdminPermissionRelation;
import com.sky.cold.admin.service.AdminPermissionRelationService;



/**
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/adminpermissionrelation")
@Api(tags = "后台用户和权限关系表(除角色中定义的权限以外的加减权限)管理")
public class AdminPermissionRelationController extends SuperController {
    @Autowired
    private AdminPermissionRelationService adminPermissionRelationService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = adminPermissionRelationService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		AdminPermissionRelation adminPermissionRelation = adminPermissionRelationService.getById(id);

        return success(adminPermissionRelation);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台用户和权限关系表(除角色中定义的权限以外的加减权限)")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody AdminPermissionRelation adminPermissionRelation){
		adminPermissionRelationService.save(adminPermissionRelation);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台用户和权限关系表(除角色中定义的权限以外的加减权限)")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody AdminPermissionRelation adminPermissionRelation){
		adminPermissionRelationService.updateById(adminPermissionRelation);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台用户和权限关系表(除角色中定义的权限以外的加减权限)")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		adminPermissionRelationService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
