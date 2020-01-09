package com.cloud.log.feign.fallback;

import com.cloud.base.entity.SysLog;
import com.cloud.core.utils.R;
import com.cloud.log.feign.RemoteLogService;
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
public class RemoteLogFallbackImpl implements RemoteLogService {

    private final Throwable throwable;

    @Override
    public R saveLog(SysLog sysLog) {
        log.error("feign 保存日志失败:{}", sysLog, throwable);
        return null;
    }
}
