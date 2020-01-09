package com.cloud.log.feign.factory;

import com.cloud.log.feign.RemoteLogService;
import com.cloud.log.feign.fallback.RemoteLogFallbackImpl;
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
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService> {
    @Override
    public RemoteLogService create(Throwable throwable) {
        return new RemoteLogFallbackImpl(throwable);
    }
}
