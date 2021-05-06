package com.sky.cold.security.handler;

import cn.hutool.json.JSONUtil;
import com.sky.cold.common.rest.responses.SuccessResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author wangchao
 * @date 2021-04-30 5:04 下午
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException exception) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Cache-Control","no-cache");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        SuccessResponses<Object> successResponses = new SuccessResponses<>();
        successResponses.setStatus(HttpStatus.UNAUTHORIZED.value());
        successResponses.setResult("登录失败");
        httpServletResponse.getWriter().println(JSONUtil.parse(successResponses));
        httpServletResponse.getWriter().flush();
    }
}
