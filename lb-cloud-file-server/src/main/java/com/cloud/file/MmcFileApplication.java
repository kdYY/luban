package com.cloud.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * @ClassName MmcFileApplication
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/9
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.mmc.file.mapper")
public class MmcFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(MmcFileApplication.class, args);
    }

    /**
     * 对上传文件的配置
     *
     * @return MultipartConfigElement配置实例
     * @date 2018年6月29日 上午10:55:02
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置单个附件大小上限值(默认为1M)
        //选择字符串作为参数的话，单位可以用MB、KB;
        factory.setMaxFileSize("50MB");
        // 设置所有附件的总大小上限值
        factory.setMaxRequestSize("250MB");
        return factory.createMultipartConfig();
    }
}
