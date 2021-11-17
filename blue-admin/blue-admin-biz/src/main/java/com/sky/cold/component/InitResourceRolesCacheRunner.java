package com.sky.cold.component;

import com.sky.cold.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangchao
 * @Date 2021/11/16 17:13
 */
@Component
@Slf4j
public class InitResourceRolesCacheRunner implements CommandLineRunner {

    @Autowired
    ResourceService resourceService;

    /**
     * CommandLineRunner项目启动初始化角色资源
     */
    @Override
    public void run(String... args) {
        resourceService.initResourceRolesMap();
    }
}
