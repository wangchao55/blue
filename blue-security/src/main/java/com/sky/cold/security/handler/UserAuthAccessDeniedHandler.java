package com.sky.cold.security.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;

/**
 * 用户无权限访问
 * @Author: wangchao
 * @Date: 2021/4/1 11:38
 */
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(javax.servlet.http.HttpServletRequest httpServletRequest,
                       javax.servlet.http.HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Cache-Control","no-cache");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        HashMap<Object, Object> map = new HashMap<>(16);
        map.put("message","未授权");
        map.put("code","401");
        httpServletResponse.getWriter().println(JSONUtil.parse(map));
        httpServletResponse.getWriter().flush();
    }
}
