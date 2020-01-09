package com.cloud.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @ClassName ServerErrorException
 * @Description:
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class ServerErrorException extends OAuth2Exception {

    public ServerErrorException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "server_error";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}
