package com.sky.cold.common.handler;

import com.sky.cold.common.exception.ApiException;
import com.sky.cold.common.rest.responses.SuccessResponses;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常捕捉
 * @Author: wangchao
 * @Date: 2021/4/1 16:37
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public SuccessResponses bizExceptionHandler(HttpServletRequest req, ApiException e){
        return new SuccessResponses(202,e.getErrorCode().getMsg());
    }

    /**
     * 校验参数合法性
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public SuccessResponses bindException(Exception e, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder("[");
        List<ObjectError> list = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
        for (ObjectError item : list) {
            sb.append(item.getDefaultMessage()).append(',');
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(']');
        return new SuccessResponses(202,sb.toString());
    }

    @ExceptionHandler(value = Exception.class)
    public SuccessResponses handle1(Exception ex) {
        ex.printStackTrace();
        return new SuccessResponses(202, ex.getMessage());
    }




}
