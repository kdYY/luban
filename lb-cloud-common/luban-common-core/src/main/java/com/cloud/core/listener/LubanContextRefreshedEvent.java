package com.cloud.core.listener;

import com.cloud.core.utils.LocalMac;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @ClassName BeanDefineConfigue
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/17
 * @Version V1.0
 **/
@Component
@Slf4j
public class LubanContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
//            String mac = null;
//            try {
//                mac = LocalMac.getLocalMac();
//                if (!StringUtils.isEmpty(mac) && mac.equals("00:0e:c6:c8:bf:4e")) {
//                    //00:0e:c6:c8:bf:4e
//                    //00:16:3e:12:cd:9f
//                    log.info("机器识别成功");
//                } else {
//                    log.error("未被识别的机器");
//                    System.exit(2);
//                }
//            } catch (SocketException e) {
//                e.printStackTrace();
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
