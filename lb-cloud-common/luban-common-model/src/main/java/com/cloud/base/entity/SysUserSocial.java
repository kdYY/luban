package com.cloud.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Classname SysSocial
 * @Description 社交实体类
 * @Author kevins
 * @Date 2019-07-17 15:57
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_social")
public class SysUserSocial implements Serializable {

    private String userId;

    private String providerId;

    private String providerUserId;

    private String displayName;

    private String imageUrl;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
