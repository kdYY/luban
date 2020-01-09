package com.cloud.base.dto;

import com.cloud.base.entity.SysUser;
import lombok.Data;

import java.util.Set;

/**
 * @Classname UserInfo
 * @Description TODO
 * @Author kevins
 * @Date 2019-08-15 11:17
 * @Version 1.0
 */
@Data
public class UserDetailsInfo {

    /**
     * 用户基本信息
     */
    private SysUser sysUser;

    /**
     * 用户权限
     */
    Set<String> permissions;
}
