package com.cloud.auth.feign.fallback;

import com.cloud.auth.feign.RemoteUserService;
import com.cloud.base.dto.UserDetailsInfo;
import com.cloud.core.utils.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Classname RemoteLogFallbackImpl
 * @Description TODO
 * @Author kevins
 * @Date 2019-08-24 10:56
 * @Version 1.0
 */
@Slf4j
@AllArgsConstructor
public class RemoteLogFallbackImpl implements RemoteUserService {

    private final Throwable throwable;

    @Override
    public R<UserDetailsInfo> info(String username) {
        log.error("feign 调用用户{},信息:{}", username, throwable.getLocalizedMessage());
        return null;
    }

    @Override
    public R<UserDetailsInfo> getUserInfoBySocial(String providerId, int providerUserId) {
        log.error("feign 调用用户{},信息:{}", providerUserId, throwable.getLocalizedMessage());
        return null;
    }
}
