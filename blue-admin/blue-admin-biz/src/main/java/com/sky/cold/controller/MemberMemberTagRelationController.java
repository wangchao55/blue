package com.sky.cold.controller;

import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.MemberMemberTagRelation;
import com.sky.cold.service.MemberMemberTagRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 用户和标签关系表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("admin/membermembertagrelation")
@Api(tags = "用户和标签关系表管理")
public class MemberMemberTagRelationController extends SuperController {

    private final MemberMemberTagRelationService memberMemberTagRelationService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = memberMemberTagRelationService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		MemberMemberTagRelation memberMemberTagRelation = memberMemberTagRelationService.getById(id);

        return success(memberMemberTagRelation);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增用户和标签关系表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody MemberMemberTagRelation memberMemberTagRelation){
		memberMemberTagRelationService.save(memberMemberTagRelation);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改用户和标签关系表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody MemberMemberTagRelation memberMemberTagRelation){
		memberMemberTagRelationService.updateById(memberMemberTagRelation);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除用户和标签关系表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		memberMemberTagRelationService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
