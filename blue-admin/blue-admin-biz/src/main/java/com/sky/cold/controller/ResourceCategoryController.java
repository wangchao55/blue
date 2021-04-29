package com.sky.cold.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.ResourceCategory;
import com.sky.cold.service.ResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 资源分类表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/resourcecategory")
@Api(tags = "资源分类表管理")
public class ResourceCategoryController extends SuperController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/getResourceCategoryList/{pageNum}/{pageSize}")
    public SuccessResponses<IPage<ResourceCategory>> getResourceCategoryList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        return success(resourceCategoryService.getResourceCategoryList(pageNum,pageSize));
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/getResourceCategoryInfo/{id}")
    public SuccessResponses<ResourceCategory> getResourceCategoryInfo(@PathVariable("id") Long id){
        return success(resourceCategoryService.getResourceCategoryInfo(id));
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增资源分类表")
    @PostMapping("/saveResourceCategoryInfo")
    public SuccessResponses<Boolean> saveResourceCategoryInfo(@RequestBody ResourceCategory resourceCategory){
        return success(	resourceCategoryService.saveResourceCategoryInfo(resourceCategory));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改资源分类表")
    @PutMapping("/updateResourceCategoryInfo")
    public SuccessResponses<Boolean> updateResourceCategoryInfo(@RequestBody ResourceCategory resourceCategory){
        return success(resourceCategoryService.updateResourceCategoryInfo(resourceCategory));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除资源分类表")
    @DeleteMapping("/delete/{id}")
    public SuccessResponses<Boolean> delete(@PathVariable("id") Long id){
        return success(resourceCategoryService.delete(id));
    }

}
