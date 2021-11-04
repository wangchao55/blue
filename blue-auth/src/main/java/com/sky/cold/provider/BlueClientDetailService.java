package com.sky.cold.provider;

import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * 
 * @author wangchao
 * @date 2021-10-30 4:51 下午
 */
public class BlueClientDetailService extends JdbcClientDetailsService {

    public BlueClientDetailService(DataSource dataSource){
        super(dataSource);
    }

}
