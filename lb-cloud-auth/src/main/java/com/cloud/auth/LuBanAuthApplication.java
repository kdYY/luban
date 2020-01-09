package com.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName MmcAuthApplication
 * @Description: TODO
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class LuBanAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuBanAuthApplication.class, args);
    }
}
