package com.cloud.auth.handler;

import com.cloud.auth.service.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @Classname AuthenticationFailureEvenHandler
 * @Description 在验证过程中发生异常会触发此类事件
 * @Author kevins
 * @Date 2019-03-20 10:42
 * @Version 1.0
 */
@Slf4j
@Component
public class AuthenticationFailureEvenHandler implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        AuthenticationException authenticationException = event.getException();
        Authentication authentication = (Authentication) event.getSource();
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUser) {
            log.info("用户：{} 登录失败，异常：{}", ((SecurityUser) principal).getUsername(), authenticationException.getLocalizedMessage());
        }
    }
}
