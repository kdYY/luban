package com.cloud.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName ValidateCodeException
 * @Description:
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}
