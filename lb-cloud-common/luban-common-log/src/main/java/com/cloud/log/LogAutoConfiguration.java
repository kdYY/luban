package com.cloud.log;

import com.cloud.log.aspect.SysLogAspect;
import com.cloud.log.listener.SysLogListener;
import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author kevins
 * @Classname LogAutoConfiguration
 * @Description 当web项目引入此依赖时，自动配置对应的内容 初始化log的事件监听与切面配置
 * @Date 2019-08-24 11:26
 * @Version 1.0
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
@EnableFeignClients({"com.cloud.log.feign"})
public class LogAutoConfiguration {

    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener();
    }

    @Bean
    public SysLogAspect sysLogAspect(ApplicationEventPublisher publisher) {
        return new SysLogAspect(publisher);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}

