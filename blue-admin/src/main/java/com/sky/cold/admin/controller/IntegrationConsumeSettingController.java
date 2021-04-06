package com.sky.cold.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.rest.responses.SuccessResponses;
import com.sky.cold.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.admin.entity.IntegrationConsumeSetting;
import com.sky.cold.admin.service.IntegrationConsumeSettingService;



/**
 * 积分消费设置
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/integrationconsumesetting")
@Api(tags = "积分消费设置管理")
public class IntegrationConsumeSettingController extends SuperController {
    @Autowired
    private IntegrationConsumeSettingService integrationConsumeSettingService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = integrationConsumeSettingService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		IntegrationConsumeSetting integrationConsumeSetting = integrationConsumeSettingService.getById(id);

        return success(integrationConsumeSetting);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增积分消费设置")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody IntegrationConsumeSetting integrationConsumeSetting){
		integrationConsumeSettingService.save(integrationConsumeSetting);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改积分消费设置")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody IntegrationConsumeSetting integrationConsumeSetting){
		integrationConsumeSettingService.updateById(integrationConsumeSetting);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除积分消费设置")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		integrationConsumeSettingService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
