package com.cloud.auth.feign;

import com.cloud.auth.feign.factory.RemoteLogFallbackFactory;
import com.cloud.base.dto.UserDetailsInfo;
import com.cloud.core.constant.ServiceNameConstants;
import com.cloud.core.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname RemoteUserService
 * @Description 远程调用用户模块
 * @Author kevins
 * @Date 2019-08-15 11:05
 * @Version 1.0
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_UMPS_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户包括角色权限等
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping("/user/info/{username}")
    R<UserDetailsInfo> info(@PathVariable("username") String username);


    /**
     * @param providerId
     * @param providerUserId
     * @return
     */
    @GetMapping("/user/social/info")
    R<UserDetailsInfo> getUserInfoBySocial(@RequestParam("providerId") String providerId, @RequestParam("providerUserId") int providerUserId);

}

