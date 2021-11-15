package com.sky.cold.config;

import com.sky.cold.common.constant.AuthConstant;
import com.sky.cold.component.JwtTokenEnhancer;
import com.sky.cold.service.BlueClientDetailService;
import com.sky.cold.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务器配置
 * @author wangchao
 * @date 2021-05-29 2:58 下午
 */
@RequiredArgsConstructor
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final String KEY_STORE_CLASS_PATH = "blue.jks";
    private final String KEY_STORE_PASSWORD = "blueblue";
    private final String KEY_STORE_ALIAS = "blue";

    private final DataSource dataSource;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenEnhancer jwtTokenEnhancer;
    private final PasswordEncoder passwordEncoder;


    /**
     * 从数据库中获取client的相关信息
     */
    @Bean
    public ClientDetailsService blueClientDetailsService(){
        BlueClientDetailService clientDetailService = new BlueClientDetailService(dataSource);
        clientDetailService.setSelectClientDetailsSql(AuthConstant.DEFAULT_SELECT_STATEMENT);
        clientDetailService.setFindClientDetailsSql(AuthConstant.DEFAULT_FIND_STATEMENT);
        return clientDetailService;
    }

    /***
     * 配置client的相关信息的处理方式为jdbc模式
     */
    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        //从数据库中获取当前客户端信息
        clients.withClientDetails(blueClientDetailsService());
    }

    /**
     * 配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        //配置JWT的内容增强器
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates);
        endpoints
                //密码管理模式
                .authenticationManager(authenticationManager)
                //配置加载用户信息的服务
                .userDetailsService(userService)
                .accessTokenConverter(accessTokenConverter())
                //令牌增强
                .tokenEnhancer(enhancerChain)
                //允许post提交
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 密钥
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(KEY_STORE_CLASS_PATH), KEY_STORE_PASSWORD.toCharArray());
        return keyStoreKeyFactory.getKeyPair(KEY_STORE_ALIAS, KEY_STORE_PASSWORD.toCharArray());
    }

    /**
     * 配置令牌端点的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()");
    }

}
