package com.cloud.log.listener;

import com.cloud.base.entity.SysLog;
import com.cloud.log.event.SysLogEvent;
import com.cloud.log.feign.RemoteLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @Classname SysLogListener
 * @Description 注解形式的监听 异步监听日志事件
 * @Author kevins
 * @Date 2019-04-28 11:34
 * @Version 1.0
 */
@Slf4j
public class SysLogListener {

    @Autowired
    private RemoteLogService remoteLogService;

    @Async
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        // 保存日志
        remoteLogService.saveLog(sysLog);
        log.info("远程日志记录成功：{}", sysLog);
    }
}
