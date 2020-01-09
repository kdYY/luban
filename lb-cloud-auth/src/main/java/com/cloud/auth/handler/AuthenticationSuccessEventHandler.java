package com.cloud.auth.handler;

import cn.hutool.core.collection.CollUtil;
import com.cloud.auth.service.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @Classname prexAuthenticationSuccessHandler
 * @Description 在验证过程中成功会触发此类事件
 * @Author kevins
 * @Date 2019-08-06 15:24
 * @Version 1.0
 */
@Slf4j
@Component
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        Authentication authentication = (Authentication) event.getSource();
        if (CollUtil.isNotEmpty(authentication.getAuthorities())) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SecurityUser) {
                log.info("用户：{} 登录成功", ((SecurityUser) principal).getUsername());
            }
        }
    }
}


