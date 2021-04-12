package com.sky.cold.controller;

import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.MemberProductCategoryRelation;
import com.sky.cold.service.MemberProductCategoryRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 会员与产品分类关系表（用户喜欢的分类）
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/memberproductcategoryrelation")
@Api(tags = "会员与产品分类关系表（用户喜欢的分类）管理")
public class MemberProductCategoryRelationController extends SuperController {
    @Autowired
    private MemberProductCategoryRelationService memberProductCategoryRelationService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = memberProductCategoryRelationService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		MemberProductCategoryRelation memberProductCategoryRelation = memberProductCategoryRelationService.getById(id);

        return success(memberProductCategoryRelation);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增会员与产品分类关系表（用户喜欢的分类）")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody MemberProductCategoryRelation memberProductCategoryRelation){
		memberProductCategoryRelationService.save(memberProductCategoryRelation);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改会员与产品分类关系表（用户喜欢的分类）")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody MemberProductCategoryRelation memberProductCategoryRelation){
		memberProductCategoryRelationService.updateById(memberProductCategoryRelation);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除会员与产品分类关系表（用户喜欢的分类）")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		memberProductCategoryRelationService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
