package com.sky.cold.controller;

import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.MemberTag;
import com.sky.cold.service.MemberTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 用户标签表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("admin/membertag")
@Api(tags = "用户标签表管理")
public class MemberTagController extends SuperController {

    private final MemberTagService memberTagService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = memberTagService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		MemberTag memberTag = memberTagService.getById(id);

        return success(memberTag);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增用户标签表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody MemberTag memberTag){
		memberTagService.save(memberTag);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改用户标签表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody MemberTag memberTag){
		memberTagService.updateById(memberTag);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除用户标签表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		memberTagService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
