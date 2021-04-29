package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.common.enums.CommonTypeEnum;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.dao.MenuDao;
import com.sky.cold.dto.MenuTreeDto;
import com.sky.cold.entity.Menu;
import com.sky.cold.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {


    /**
     * 获取菜单列表
     */
    @Override
    public IPage<Menu> getMenuList(int pageNum, int pageSize,int parentId) {
        IPage<Menu> page = new Page<>(pageNum, pageSize);
        return new Menu().selectPage(page, Wrappers.<Menu>query().lambda().eq(Menu::getParentId,parentId));
    }

    /**
     * 获取详情
     */
    @Override
    public Menu getMenuInfo(Long id) {
        Menu menu = new Menu().selectById(id);
        ApiAssert.notNull(ErrorCodeEnum.MENU_NOT_FOUND,menu);
        return menu;
    }

    /**
     * 保存菜单信息
     */
    @Override
    public Boolean saveMenuInfo(Menu menu) {
        Menu info = new Menu().selectOne(Wrappers.<Menu>query().lambda().eq(Menu::getName, menu.getName()));
        ApiAssert.notNull(ErrorCodeEnum.MENU_ALREADY_EXISTS,info);
        info.setCreateTime(new Date());
        return info.insert();
    }

    /**
     * 修改菜单信息
     */
    @Override
    public Boolean updateMenuInfo(Menu menu) {
        Menu info = new Menu().selectById(menu.getId());
        ApiAssert.notNull(ErrorCodeEnum.MENU_NOT_FOUND,info);
        return menu.updateById();
    }

    /**
     * 删除菜单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteMenuInfo(Long[] ids) {
        Arrays.stream(ids).collect(Collectors.toList()).stream().map(id -> {
            Menu menu = this.getMenuInfo(id);
            menu.deleteById(id);
            if(menu.getLevel() == CommonTypeEnum.ZERO.getCode()){
                menu.delete(Wrappers.<Menu>query().lambda().eq(Menu::getParentId,menu.getId()));
            }
            return menu;
        }).collect(Collectors.toList());
        return true;
    }

    /**
     * 菜单树状节点
     */
    @Override
    public List<MenuTreeDto> getMenuTreeList() {
        List<Menu> menuList = new Menu().selectList(Wrappers.<Menu>query().lambda());
        return menuList.stream()
                .filter(menu -> menu.getParentId().equals(CommonTypeEnum.ZERO.getCode()))
                .map(menu -> getMenuTreeDto(menu,menuList)).collect(Collectors.toList());
    }

    /**
     * 修改菜单状态
     */
    @Override
    public Boolean updateMenuStatus(Long menuId) {
        Menu menu = this.getMenuInfo(menuId);
        menu.setHidden(menu.getHidden() == CommonTypeEnum.ZERO.getCode() ? CommonTypeEnum.ONE.getCode() : CommonTypeEnum.ZERO.getCode());
        return menu.updateById();
    }

    private MenuTreeDto getMenuTreeDto(Menu menu, List<Menu> menuList) {
        MenuTreeDto menuTreeDto = new MenuTreeDto();
        BeanUtils.copyProperties(menu,menuTreeDto);
        List<MenuTreeDto> menuChildren = menuList.stream()
                .filter(menu1 -> menu1.getParentId().equals(menu.getId()))
                .map(menu1 -> getMenuTreeDto(menu1, menuList)).collect(Collectors.toList());
        menuTreeDto.setChildren(menuChildren);
        return menuTreeDto;
    }
}