package com.sky.cold.security.util;

import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.exception.ApiException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

/**
 * @Author: wangchao
 * @Date: 2021/4/2 11:27
 */
@Configuration
public class SecurityUtil {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new ApiException(ErrorCodeEnum.UNAUTHORIZED);
        }
        return authentication;
    }

    /**
     * 从Authentication获取用户名称
     */
    public String getUser() {
        return getUser(getAuthentication());
    }

    /**
     * 获取用户名称
     */
    public String getUser(Authentication authentication) {
        Principal principal = (Principal) authentication.getPrincipal();
        return principal.getName();
    }

    /**
     * 获取用户信息
     */
    public Principal getUserInfo() {
        return (Principal) getAuthentication().getPrincipal();
    }

    /**
     * 获取当前登录用户信息
     */

}
