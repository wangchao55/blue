package com.sky.cold.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.common.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.entity.Member;
import com.sky.cold.service.MemberService;



/**
 * 会员表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/member")
@Api(tags = "会员表管理")
public class MemberController extends SuperController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = memberService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		Member member = memberService.getById(id);

        return success(member);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增会员表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody Member member){
		memberService.save(member);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改会员表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody Member member){
		memberService.updateById(member);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除会员表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
