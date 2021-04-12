package com.sky.cold.controller;

import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.IntegrationChangeHistory;
import com.sky.cold.service.IntegrationChangeHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 积分变化历史记录表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/integrationchangehistory")
@Api(tags = "积分变化历史记录表管理")
public class IntegrationChangeHistoryController extends SuperController {
    @Autowired
    private IntegrationChangeHistoryService integrationChangeHistoryService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = integrationChangeHistoryService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		IntegrationChangeHistory integrationChangeHistory = integrationChangeHistoryService.getById(id);

        return success(integrationChangeHistory);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增积分变化历史记录表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody IntegrationChangeHistory integrationChangeHistory){
		integrationChangeHistoryService.save(integrationChangeHistory);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改积分变化历史记录表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody IntegrationChangeHistory integrationChangeHistory){
		integrationChangeHistoryService.updateById(integrationChangeHistory);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除积分变化历史记录表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		integrationChangeHistoryService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
