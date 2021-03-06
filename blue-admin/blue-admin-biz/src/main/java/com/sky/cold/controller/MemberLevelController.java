package com.sky.cold.controller;

import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.MemberLevel;
import com.sky.cold.service.MemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 会员等级表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("admin/memberlevel")
@Api(tags = "会员等级表管理")
public class MemberLevelController extends SuperController {

    private final MemberLevelService memberLevelService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = memberLevelService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		MemberLevel memberLevel = memberLevelService.getById(id);

        return success(memberLevel);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增会员等级表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody MemberLevel memberLevel){
		memberLevelService.save(memberLevel);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改会员等级表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody MemberLevel memberLevel){
		memberLevelService.updateById(memberLevel);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除会员等级表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		memberLevelService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
