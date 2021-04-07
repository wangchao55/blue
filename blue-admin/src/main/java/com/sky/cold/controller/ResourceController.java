package com.sky.cold.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.common.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.entity.Resource;
import com.sky.cold.service.ResourceService;



/**
 * 后台资源表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/resource")
@Api(tags = "后台资源表管理")
public class ResourceController extends SuperController {
    @Autowired
    private ResourceService resourceService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = resourceService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		Resource resource = resourceService.getById(id);

        return success(resource);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台资源表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody Resource resource){
		resourceService.save(resource);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台资源表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody Resource resource){
		resourceService.updateById(resource);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台资源表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		resourceService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
