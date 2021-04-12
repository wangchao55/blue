package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MenuDao;
import com.sky.cold.entity.Menu;
import com.sky.cold.service.MenuService;
import org.springframework.stereotype.Service;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {



}