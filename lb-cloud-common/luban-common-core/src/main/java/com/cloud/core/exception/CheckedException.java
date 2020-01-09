package com.cloud.core.exception;

import lombok.NoArgsConstructor;

/**
 * @ClassName Constant
 * @Description: 自定义检查异常
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
@NoArgsConstructor
public class CheckedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
