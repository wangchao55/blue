package com.sky.cold.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.common.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.admin.entity.GrowthChangeHistory;
import com.sky.cold.admin.service.GrowthChangeHistoryService;



/**
 * 成长值变化历史记录表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/growthchangehistory")
@Api(tags = "成长值变化历史记录表管理")
public class GrowthChangeHistoryController extends SuperController {
    @Autowired
    private GrowthChangeHistoryService growthChangeHistoryService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = growthChangeHistoryService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		GrowthChangeHistory growthChangeHistory = growthChangeHistoryService.getById(id);

        return success(growthChangeHistory);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增成长值变化历史记录表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody GrowthChangeHistory growthChangeHistory){
		growthChangeHistoryService.save(growthChangeHistory);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改成长值变化历史记录表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody GrowthChangeHistory growthChangeHistory){
		growthChangeHistoryService.updateById(growthChangeHistory);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除成长值变化历史记录表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		growthChangeHistoryService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
