package com.sky.cold.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.dto.MenuTreeDto;
import com.sky.cold.entity.Menu;
import com.sky.cold.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


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
    @ApiOperation(value = "菜单列表", notes = "列表查询")
    @GetMapping("/getMenuList/{pageNum}/{pageSize}/{parentId}")
    public SuccessResponses<IPage<Menu>> getMenuList(@PathVariable("pageNum") int pageNum,
                                                     @PathVariable("pageSize") int pageSize,
                                                     @PathVariable("parentId") int parentId){
        return success(menuService.getMenuList(pageNum,pageSize,parentId));
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id查询")
    @GetMapping("/getMenuInfo/{id}")
    public SuccessResponses<Menu> getMenuInfo(@PathVariable("id") Long id){
        return success(menuService.getMenuInfo(id));
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增后台菜单表")
    @PostMapping("/saveMenuInfo")
    public SuccessResponses<Boolean> saveMenuInfo(@RequestBody Menu menu){
        return success(menuService.saveMenuInfo(menu));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台菜单表")
    @PutMapping("/updateMenuInfo")
    public SuccessResponses<Boolean> updateMenuInfo(@RequestBody Menu menu){
        return success(menuService.updateMenuInfo(menu));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台菜单表")
    @DeleteMapping("/deleteMenuInfo")
    public SuccessResponses<Boolean> deleteMenuInfo(@RequestBody Long[] ids){
        return success(menuService.deleteMenuInfo(ids));
    }

    /**
     * 菜单树状节点
     */
    @ApiOperation(value = "菜单树状节点")
    @GetMapping("/getMenuTreeList")
    public SuccessResponses<List<MenuTreeDto>> getMenuTreeList(){
        return success(menuService.getMenuTreeList());
    }

    /**
     * 修改菜单状态
     */
    @ApiOperation(value = "修改菜单状态")
    @PutMapping("/updateMenuStatus/{menuId}")
    public SuccessResponses<Boolean> updateMenuStatus(@PathVariable("menuId") Long menuId){
        return success(menuService.updateMenuStatus(menuId));
    }

}
