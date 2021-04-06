package com.sky.cold.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.sky.cold.handler.MyMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 配置MybatisPlus及数据源相关.
 *
 * @author : NanCheung
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@MapperScan("com.sky.cold.**.dao.*.dao")
@Import({MyMetaObjectHandler.class})
public class MybatisPlusAutoConfiguration {
    
    /**
     * mybatis-plus分页插件
     */
    @Bean
    @ConditionalOnMissingBean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(DbType.MYSQL.getDb());
        return paginationInterceptor;
    }
    
    /**
     * 自动配置MybatisPlus参数
     */
    @Bean
    @Primary
    public MybatisPlusProperties mybatisPlusProperties() {
        MybatisPlusProperties mybatisPlusProperties = new MybatisPlusProperties();
        //MAPPER.XML所在位置
        mybatisPlusProperties.setMapperLocations(new String[]{"classpath*:com/**/mapper/**/*.xml"});
        //扫描实体类，自动注册别名
        mybatisPlusProperties.setTypeAliasesPackage("com.sky.cold.**.entity");
        
        mybatisPlusProperties.setGlobalConfig(globalConfig());
        mybatisPlusProperties.setConfiguration(mybatisConfiguration());
        return mybatisPlusProperties;
    }
    
    private GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setIdType(IdType.AUTO)
                //逻辑删除值
                .setLogicDeleteValue("-1")
                //没有被逻辑删除值
                .setLogicNotDeleteValue("1");
        
        globalConfig
                .setDbConfig(dbConfig)
                //禁止MP打印banner
                .setBanner(false);
        
        return globalConfig;
    }
    
    private MybatisConfiguration mybatisConfiguration() {
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        //查询结果下划线转驼峰
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        //禁用二级缓存
        mybatisConfiguration.setCacheEnabled(false);
        //查询结果为空 也显示出来
        mybatisConfiguration.setCallSettersOnNulls(true);
        return mybatisConfiguration;
    }
}
