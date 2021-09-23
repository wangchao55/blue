package com.sky.cold.config;

import com.sky.cold.common.config.BaseSwaggerConfig;
import com.sky.cold.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by wangchao on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.sky.cold.controller")
                .title("blue认证中心")
                .description("blue认证中心相关接口文档")
                .contactName("wangchao")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
