package com.cloud.log.feign;

import com.cloud.base.entity.SysLog;
import com.cloud.core.utils.R;
import com.cloud.log.feign.factory.RemoteLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Classname RemoteLogService
 * @Description TODO
 * @Author kevins
 * @Date 2019-08-24 10:53
 * @Version 1.0
 */
@FeignClient(contextId = "remoteLogService", value = "mmc-system-base-server", fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {

    @PostMapping(value = "/log")
    R saveLog(@RequestBody SysLog sysLog);
}
