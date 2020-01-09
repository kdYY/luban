package com.cloud.core.exception;

/**
 * @ClassName Constant
 * @Description: 常量
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
public class ValidateCodeException extends Exception {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException() {
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
