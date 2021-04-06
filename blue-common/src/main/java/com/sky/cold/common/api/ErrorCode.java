package com.sky.cold.common.api;

import lombok.*;

/**
 * 业务异常类
 *
 * @author NanCheung
 */
@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ErrorCode {

    /**
     * 异常信息
     */
    private String error;
    /**
     * http状态码
     */
    private int httpCode;
    /**
     * 是否展示
     */
    @Builder.Default
    private boolean show=true;
    /**
     * 输出的错误消息
     */
    private String msg;
    
    /**
     * 构建一个自定义的异常
     *
     * @param httpCode 自定义响应状态
     * @param msg      自定义消息
     * @return {@link ErrorCode}
     */
    public static ErrorCode buildErrorCode(int httpCode, String msg) {
        return ErrorCode.builder()
                .httpCode(httpCode)
                .show(true)
                .error("自定义异常")
                .msg(msg)
                .build();
    }
}
