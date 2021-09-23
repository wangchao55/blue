package com.sky.cold.config;

import com.sky.cold.entity.Resource;
import com.sky.cold.security.component.DynamicSecurityService;
import com.sky.cold.security.config.SecurityConfig;
import com.sky.cold.service.AdminService;
import com.sky.cold.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * admin后台权限配置
 * @Author: wangchao
 * @Date: 2021/4/1 15:24
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BlueSecurityConfig extends SecurityConfig {
    @Autowired
    private AdminService adminService;

    @Autowired
    ResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> adminService.loadUserByUserName(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<Resource> resourceList = resourceService.getResourceListAll();
                resourceList.parallelStream()
                        .map(resource -> map.put(resource.getUrl(),new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName())))
                        .collect(Collectors.toList());
                /*for (Resource resource : resourceList) {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }*/
                return map;
            }
        };
    }
}
