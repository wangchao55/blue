package com.sky.cold.security.handler;

import cn.hutool.json.JSONUtil;
import com.sky.cold.common.rest.responses.SuccessResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 用户未登录
 * @Author: wangchao
 * @Date: 2021/4/1 14:09
 */
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Cache-Control","no-cache");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        SuccessResponses<Object> successResponses = new SuccessResponses<>();
        successResponses.setStatus(HttpStatus.UNAUTHORIZED.value());
        successResponses.setResult("未登录");
        httpServletResponse.getWriter().println(JSONUtil.parse(successResponses));
        httpServletResponse.getWriter().flush();
    }
}
