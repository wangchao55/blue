package com.sky.cold.controller;

import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.MemberReceiveAddress;
import com.sky.cold.service.MemberReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 会员收货地址表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/memberreceiveaddress")
@Api(tags = "会员收货地址表管理")
public class MemberReceiveAddressController extends SuperController {
    @Autowired
    private MemberReceiveAddressService memberReceiveAddressService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = memberReceiveAddressService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		MemberReceiveAddress memberReceiveAddress = memberReceiveAddressService.getById(id);

        return success(memberReceiveAddress);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增会员收货地址表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody MemberReceiveAddress memberReceiveAddress){
		memberReceiveAddressService.save(memberReceiveAddress);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改会员收货地址表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody MemberReceiveAddress memberReceiveAddress){
		memberReceiveAddressService.updateById(memberReceiveAddress);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除会员收货地址表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		memberReceiveAddressService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
