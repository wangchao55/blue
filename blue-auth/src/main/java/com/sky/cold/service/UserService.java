package com.sky.cold.service;

import com.sky.cold.common.constant.AuthConstant;
import com.sky.cold.common.entity.dto.UserInfoDto;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.constant.MessageConstant;
import com.sky.cold.domain.SecurityUser;
import com.sky.cold.feign.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangchao
 * @date 2021-05-29 10:40 上午
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    AdminService adminService;


    /**
     * 加载用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        UserInfoDto userInfoDto = null;
        if(AuthConstant.ADMIN_CLIENT_ID.equals(clientId)){
            SuccessResponses<UserInfoDto> userInfoDtoSuccessResponses = adminService.loadUserByUsername(username);
            userInfoDto = userInfoDtoSuccessResponses.getResult();
        }else{
            //userInfoDto = memberService.loadUserByUsername(username);
        }
        if (userInfoDto==null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userInfoDto.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userInfoDto);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;    }
}
