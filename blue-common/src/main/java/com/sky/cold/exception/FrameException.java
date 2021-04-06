package com.sky.cold.exception;

/**
 * <p>
 * 框架内部异常类
 * </p>
 *
 * @author wangchao
 */
public class FrameException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FrameException(String message) {
        super(message);
    }

    public FrameException(Throwable throwable) {
        super(throwable);
    }

    public FrameException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
