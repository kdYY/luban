package com.cloud.auth.handler;


import com.cloud.auth.exception.ValidateCodeException;
import com.cloud.auth.util.SecurityUtil;
import com.cloud.core.utils.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname FebsAuthenticationFailureHandler
 * @Description 认证失败处理器
 * @Author kevins
 * @Date 2019-07-07 23:45
 * @Version 1.0
 */
@Component
public class MmcAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        String message;
        if (exception instanceof ValidateCodeException) {
            message = exception.getMessage();
        } else {
            message = "认证失败，请联系网站管理员";
        }
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        SecurityUtil.writeJavaScript(R.error(message), response);
    }
}


