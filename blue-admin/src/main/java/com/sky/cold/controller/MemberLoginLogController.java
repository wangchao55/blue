package com.sky.cold.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.common.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.entity.MemberLoginLog;
import com.sky.cold.service.MemberLoginLogService;



/**
 * 会员登录记录
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/memberloginlog")
@Api(tags = "会员登录记录管理")
public class MemberLoginLogController extends SuperController {
    @Autowired
    private MemberLoginLogService memberLoginLogService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = memberLoginLogService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		MemberLoginLog memberLoginLog = memberLoginLogService.getById(id);

        return success(memberLoginLog);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增会员登录记录")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody MemberLoginLog memberLoginLog){
		memberLoginLogService.save(memberLoginLog);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改会员登录记录")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody MemberLoginLog memberLoginLog){
		memberLoginLogService.updateById(memberLoginLog);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除会员登录记录")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		memberLoginLogService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
