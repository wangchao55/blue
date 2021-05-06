package com.sky.cold.security.filter;

import com.sky.cold.common.enums.ErrorCodeEnum;
import com.sky.cold.common.service.RedisService;
import com.sky.cold.common.util.ApiAssert;
import com.sky.cold.security.handler.LoginFailureHandler;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangchao
 * @date 2021-04-30 4:54 下午
 */
public class CaptchaFilter extends OncePerRequestFilter {
    private final String loginUrl = "/login";
    @Autowired
    RedisService redisService;
    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if (loginUrl.equals(url) && request.getMethod().equals("POST")) {
            //log.info("获取到login链接，正在校验验证码 -- " + url);
            try {
                validate(request);
            } catch (AuthenticationException e) {
                //log.info(e.getMessage());
                //交给登录失败处理器处理
                loginFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) {
        String code = request.getParameter("code");
        String token = request.getParameter("token");
        if (StringUtils.isBlank(code) || StringUtils.isBlank(token)) {
            ApiAssert.failure(ErrorCodeEnum.VERIFICATION_CODE_NOT_EMPTY);
        }
        if (!code.equals(redisService.hGet("captcha", token))) {
            ApiAssert.failure(ErrorCodeEnum.VERIFICATION_CODE_INCONSISTENT);
        }
        redisService.hDel("captcha", token);
    }
}
