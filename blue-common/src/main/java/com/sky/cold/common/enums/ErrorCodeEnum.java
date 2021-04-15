package com.sky.cold.common.enums;


import com.sky.cold.common.api.ErrorCode;
import com.sky.cold.common.exception.FrameException;

import javax.servlet.http.HttpServletResponse;

/**
 * 业务异常枚举
 *
 * @author NanCheung
 */
public enum ErrorCodeEnum {

    /**
     * 400
     * 请求参数错误或不完整
     */
    BAD_REQUEST(HttpServletResponse.SC_BAD_REQUEST, true, "请求参数错误或不完整"),
    /**
     * 400
     * JSON格式错误
     */
    JSON_FORMAT_ERROR(HttpServletResponse.SC_BAD_REQUEST, true, "JSON格式错误"),
    /**
     * 401
     * 请先进行认证
     */
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, true, "请先进行认证"),
    /**
     * 403
     * 无权查看
     */
    FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, true, "无权查看"),
    /**
     * 404
     * 未找到该路径
     */
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, true, "未找到该路径"),
    /**
     * 405
     * 请求方式不支持
     */
    METHOD_NOT_ALLOWED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, true, "请求方式不支持"),
    /**
     * 406
     * 不可接受该请求
     */
    NOT_ACCEPTABLE(HttpServletResponse.SC_NOT_ACCEPTABLE, true, "不可接受该请求"),
    /**
     * 411
     * 长度受限制
     */
    LENGTH_REQUIRED(HttpServletResponse.SC_LENGTH_REQUIRED, true, "长度受限制"),
    /**
     * 415
     * 不支持的媒体类型
     */
    UNSUPPORTED_MEDIA_TYPE(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, true, "不支持的媒体类型"),
    /**
     * 416
     * 不能满足请求的范围
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, true, "不能满足请求的范围"),
    /**
     * 500
     * 服务器正在升级，请耐心等待
     */
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, true, "服务器正在升级，请耐心等待"),
    /**
     * 503
     * 请求超时
     */
    SERVICE_UNAVAILABLE(HttpServletResponse.SC_SERVICE_UNAVAILABLE, true, "请求超时"),
    
    //----------------------------------------------------业务异常----------------------------------------------------
    /**
     * 找不到此地区
     */
    AREA_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND,true,"找不到此地区"),
    
    /**
     * 用户名密码错误
     */
    USERNAME_OR_PASSWORD_IS_WRONG(HttpServletResponse.SC_BAD_REQUEST, true, "用户名密码错误"),
    /**
     * 用户被禁用
     */
    USER_IS_DISABLED(HttpServletResponse.SC_NOT_ACCEPTABLE, true, "用户被禁用"),
    /**
     * 未找到该用户
     */
    USER_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, true, "未找到该用户"),

    /**
     * 未找到该角色
     */
    ROLE_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, true, "未找到该角色"),

    /**
     * 未找到该菜单
     */
    MENU_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, true, "未找到该菜单"),
    /**
     * 原密码不正确
     */
    ORIGINAL_PASSWORD_IS_INCORRECT(HttpServletResponse.SC_BAD_REQUEST, true, "原密码不正确"),

    /**
     * 新密码不一致
     */
    NEW_PASSWORD_NOT_CONSISTENT(HttpServletResponse.SC_BAD_REQUEST,true,"两次密码不一致"),

    /**
     * 用户名已存在
     */
    USERNAME_ALREADY_EXISTS(HttpServletResponse.SC_BAD_REQUEST, true, "用户名已存在"),

    /**
     * 角色已存在
     */
    ROLE_ALREADY_EXISTS(HttpServletResponse.SC_BAD_REQUEST, true, "角色已存在"),

    /**
     * token已过期
     */
    TOKEN_HAS_EXPIRED(HttpServletResponse.SC_ACCEPTED,true,"token已过期"),
    ;




    private final int httpCode;
    private final boolean show;
    private final String msg;
    
    /**
     * 错误消息体
     * @param httpCode 状态码
     * @param show 是否展示
     * @param msg 错误内容
     */
    ErrorCodeEnum(int httpCode, boolean show, String msg) {
        this.httpCode = httpCode;
        this.msg = msg;
        this.show = show;
    }

    /**
     * 转换为ErrorCode(自定义返回消息)
     *
     * @param msg
     * @return
     */
    public ErrorCode convert(String msg) {
        return ErrorCode.builder().httpCode(httpCode()).show(show()).error(name()).msg(msg).build();
    }

    /**
     * 转换为ErrorCode
     *
     * @return
     */
    public ErrorCode convert() {
        return ErrorCode.builder().httpCode(httpCode()).show(show()).error(name()).msg(msg()).build();
    }

    public static ErrorCodeEnum getErrorCode(String errorCode) {
        ErrorCodeEnum[] enums = ErrorCodeEnum.values();
        for (ErrorCodeEnum errorCodeEnum : enums) {
            if (errorCodeEnum.name().equalsIgnoreCase(errorCode)) {
                return errorCodeEnum;
            }
        }
        throw new FrameException("Error: Unknown errorCode, or do not support changing errorCode!\n");
    }

    public int httpCode() {
        return this.httpCode;
    }

    public String msg() {
        return this.msg;
    }

    public boolean show() {
        return this.show;
    }

}
