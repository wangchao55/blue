package com.sky.cold.controller;

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
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = resourceCategoryService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		ResourceCategory resourceCategory = resourceCategoryService.getById(id);

        return success(resourceCategory);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增资源分类表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody ResourceCategory resourceCategory){
		resourceCategoryService.save(resourceCategory);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改资源分类表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody ResourceCategory resourceCategory){
		resourceCategoryService.updateById(resourceCategory);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除资源分类表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		resourceCategoryService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
