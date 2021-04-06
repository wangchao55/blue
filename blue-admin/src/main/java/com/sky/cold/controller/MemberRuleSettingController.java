package com.sky.cold.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.common.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.admin.entity.MemberRuleSetting;
import com.sky.cold.admin.service.MemberRuleSettingService;



/**
 * 会员积分成长规则表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/memberrulesetting")
@Api(tags = "会员积分成长规则表管理")
public class MemberRuleSettingController extends SuperController {
    @Autowired
    private MemberRuleSettingService memberRuleSettingService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = memberRuleSettingService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		MemberRuleSetting memberRuleSetting = memberRuleSettingService.getById(id);

        return success(memberRuleSetting);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增会员积分成长规则表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody MemberRuleSetting memberRuleSetting){
		memberRuleSettingService.save(memberRuleSetting);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改会员积分成长规则表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody MemberRuleSetting memberRuleSetting){
		memberRuleSettingService.updateById(memberRuleSetting);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除会员积分成长规则表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		memberRuleSettingService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
