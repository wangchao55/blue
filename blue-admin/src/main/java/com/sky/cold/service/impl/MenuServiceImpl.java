package com.sky.cold.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MenuDao;
import com.sky.cold.entity.Menu;
import com.sky.cold.service.MenuService;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {



}