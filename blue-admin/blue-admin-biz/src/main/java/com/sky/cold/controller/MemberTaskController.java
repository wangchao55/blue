package com.sky.cold.controller;

import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.MemberTask;
import com.sky.cold.service.MemberTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 会员任务表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/membertask")
@Api(tags = "会员任务表管理")
public class MemberTaskController extends SuperController {
    @Autowired
    private MemberTaskService memberTaskService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = memberTaskService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		MemberTask memberTask = memberTaskService.getById(id);

        return success(memberTask);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增会员任务表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody MemberTask memberTask){
		memberTaskService.save(memberTask);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改会员任务表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody MemberTask memberTask){
		memberTaskService.updateById(memberTask);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除会员任务表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		memberTaskService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
