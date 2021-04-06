package com.sky.cold.admin.config;


import com.sky.cold.config.BaseSwaggerConfig;
import com.sky.cold.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by macro on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.sky.cold.admin.controller")
                .title("blue后台系统")
                .description("blue后台相关接口文档")
                .contactName("wangchao")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
