package com.cloud.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName MmcSystemBaseApplication
 * @Description: TODO
 * @Author kevins
 * @Date 2019/9/17
 * @Version V1.0
 **/
@EnableDiscoveryClient
@MapperScan("com.mmc.base.mapper")
@SpringBootApplication
public class LuBanCloudBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuBanCloudBaseApplication.class, args);
    }
}
