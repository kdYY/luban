package com.cloud.auth.util;

import com.cloud.auth.service.LoginType;
import com.cloud.core.constant.SecurityConstant;

/**
 * @ClassName LoginTypeUtil
 * @Description: TODO
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
public class LoginTypeUtil {
    public static LoginType getLoginType(String state) {
        if (state.equals(SecurityConstant.LOGIN_QQ)) {
            return LoginType.qq;
        } else if (state.equals(SecurityConstant.LOGIN_WEIXIN)) {
            return LoginType.weixin;
        } else if (state.equals(SecurityConstant.LOGIN_GITEE)) {
            return LoginType.gitee;
        }
        return LoginType.github;
    }
}
