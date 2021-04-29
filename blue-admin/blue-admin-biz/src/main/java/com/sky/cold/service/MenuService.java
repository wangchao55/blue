package com.sky.cold.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.cold.dto.MenuTreeDto;
import com.sky.cold.entity.Menu;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * 后台菜单表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
public interface MenuService extends IService<Menu> {

    IPage<Menu> getMenuList(int pageNum, int pageSize,int parentId);

    Menu getMenuInfo(Long id);

    Boolean saveMenuInfo(Menu menu);

    Boolean updateMenuInfo(Menu menu);

    Boolean deleteMenuInfo(Long[] ids);

    List<MenuTreeDto> getMenuTreeList();

    Boolean updateMenuStatus(Long menuId);
}

