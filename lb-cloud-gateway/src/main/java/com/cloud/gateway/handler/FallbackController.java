package com.cloud.gateway.handler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname FallbackController
 * @Description TODO
 * @Author kevins
 * @Date 2019-09-06 16:06
 * @Version 1.0
 */
@RestController
public class FallbackController {

    @RequestMapping("/defallback")
    public String fallback() {
        return "fallback";
    }
}

