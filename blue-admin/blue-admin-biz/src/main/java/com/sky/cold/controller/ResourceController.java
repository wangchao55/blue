package com.sky.cold.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.Resource;
import com.sky.cold.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping("/getResourceList")
    public SuccessResponses<IPage<Resource>> getResourceList(@RequestParam(required = false) Long categoryId,
                                                  @RequestParam(required = false) String resourceName,
                                                  @RequestParam(required = false) String resourceUrl,
                                                  @RequestParam Integer pageNum,
                                                  @RequestParam Integer pageSize){
        return success(resourceService.getResourceList(pageNum,pageSize,categoryId,resourceName,resourceUrl));
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/getResourceInfo/{id}")
    public SuccessResponses<Resource> getResourceInfo(@PathVariable("id") Long id){
        return success(resourceService.getResourceInfo(id));
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台资源表")
    @PostMapping("/saveResourceInfo")
    public SuccessResponses<Boolean> saveResourceInfo(@RequestBody Resource resource){
        return success(resourceService.saveResourceInfo(resource));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台资源表")
    @PutMapping("/updateResourceInfo")
    public SuccessResponses<Boolean> updateResourceInfo(@RequestBody Resource resource){
        return success(resourceService.updateResourceInfo(resource));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台资源表")
    @DeleteMapping("/deleteResourceInfo/{id}")
    public SuccessResponses<Boolean> deleteResourceInfo(@PathVariable("id") Long id){
        return success(resourceService.deleteResourceInfo(id));
    }

    /**
     * 获取资源列表
     */
    @ApiOperation(value = "获取资源列表")
    @GetMapping("/getResourceListAll")
    public SuccessResponses<List<Resource>> getResourceListAll(){
        return success(resourceService.getResourceListAll());
    }
}
