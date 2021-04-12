package com.sky.cold.controller;

import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.MemberStatisticsInfo;
import com.sky.cold.service.MemberStatisticsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 会员统计信息
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/memberstatisticsinfo")
@Api(tags = "会员统计信息管理")
public class MemberStatisticsInfoController extends SuperController {
    @Autowired
    private MemberStatisticsInfoService memberStatisticsInfoService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = memberStatisticsInfoService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		MemberStatisticsInfo memberStatisticsInfo = memberStatisticsInfoService.getById(id);

        return success(memberStatisticsInfo);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增会员统计信息")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody MemberStatisticsInfo memberStatisticsInfo){
		memberStatisticsInfoService.save(memberStatisticsInfo);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改会员统计信息")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody MemberStatisticsInfo memberStatisticsInfo){
		memberStatisticsInfoService.updateById(memberStatisticsInfo);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除会员统计信息")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		memberStatisticsInfoService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
