package com.sky.cold.common.rest.controller;


import com.google.common.base.CaseFormat;
import com.sky.cold.common.rest.filter.AntiSQLFilter;
import com.sky.cold.common.rest.responses.SuccessResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * SuperController
 * <P>Controller继承此类使用</P>
 *
 * @author wangchao
 */
public class SuperController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;


    /**
     * 成功返回
     *
     * @param object
     * @return
     */
    public <T> SuccessResponses<T> success(T object) {
        return SuccessResponses.<T>builder().status(HttpStatus.OK.value()).result(object).build();
    }

    /**
     * 成功返回
     *
     * @return
     */
    public SuccessResponses<Void> success() {
        return success(HttpStatus.OK);
    }

    /**
     * 成功返回
     *
     * @param status
     * @param object
     * @return
     */
    public <T> SuccessResponses<T> success(HttpStatus status, T object) {
        return SuccessResponses.<T>builder().status(status.value()).result(object).build();
    }


    /**
     * 成功返回
     *
     * @param status
     * @return
     */
    public SuccessResponses<Void> success(HttpStatus status) {
        response.setStatus(status.value());
        return SuccessResponses.<Void>builder().status(status.value()).build();
    }

    /**
     * 获取安全参数(SQL ORDER BY 过滤)
     *
     * @param parameter
     * @return
     */
    protected String[] getParameterSafeValues(String parameter) {
        String[] safeValues = AntiSQLFilter.getSafeValues(request.getParameterValues(parameter));
        if (safeValues == null) {
            return null;
        }
        
        //驼峰式转下划线
        return Arrays.stream(safeValues).map(s -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s)).toArray(String[]::new);
    }

}
