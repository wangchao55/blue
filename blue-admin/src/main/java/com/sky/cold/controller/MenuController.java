package com.sky.cold.controller;

import java.util.Arrays;
import java.util.Map;

import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.common.rest.controller.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.sky.cold.entity.Menu;
import com.sky.cold.service.MenuService;



/**
 * 后台菜单表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@RestController
@RequestMapping("admin/menu")
@Api(tags = "后台菜单表管理")
public class MenuController extends SuperController {
    @Autowired
    private MenuService menuService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = menuService.list();

        return success(page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/info/{id}")
    public SuccessResponses info(@PathVariable("id") Long id){
		Menu menu = menuService.getById(id);

        return success(menu);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台菜单表")
    @PostMapping("/save")
    public SuccessResponses save(@RequestBody Menu menu){
		menuService.save(menu);

        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台菜单表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody Menu menu){
		menuService.updateById(menu);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台菜单表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		menuService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
