package com.sky.cold.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.rest.responses.SuccessResponses;
import com.sky.cold.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.admin.entity.AdminLoginLog;
import com.sky.cold.admin.service.AdminLoginLogService;



/**
 * 后台用户登录日志表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/adminloginlog")
@Api(tags = "后台用户登录日志表管理")
public class AdminLoginLogController extends SuperController {
    @Autowired
    private AdminLoginLogService adminLoginLogService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = adminLoginLogService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		AdminLoginLog adminLoginLog = adminLoginLogService.getById(id);

        return success(adminLoginLog);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台用户登录日志表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody AdminLoginLog adminLoginLog){
		adminLoginLogService.save(adminLoginLog);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台用户登录日志表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody AdminLoginLog adminLoginLog){
		adminLoginLogService.updateById(adminLoginLog);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台用户登录日志表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		adminLoginLogService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
