package com.sky.cold.util;

import com.sky.cold.bo.AdminUserDetails;
import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.exception.ApiException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
        AdminUserDetails principal = (AdminUserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }

    /**
     * 获取用户信息
     */
    public AdminUserDetails getUserInfo() {
        return (AdminUserDetails) getAuthentication().getPrincipal();
    }


}
