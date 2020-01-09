package com.cloud.auth.feign.factory;

import com.cloud.auth.feign.RemoteUserService;
import com.cloud.auth.feign.fallback.RemoteLogFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname RemoteLogFallbackFactory
 * @Description TODO
 * @Author kevins
 * @Date 2019-08-24 10:55
 * @Version 1.0
 */
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteUserService> {
    @Override
    public RemoteUserService create(Throwable throwable) {
        return new RemoteLogFallbackImpl(throwable);
    }
}
