package com.cloud.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname SysOauthClientDetails
 * @Description TODO
 * @Author kevins
 * @Date 2019-09-05 09:10
 * @Version 1.0
 */
@Data
public class SysOauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用于唯一标识每一个客户端(client)
     */
    @TableId(value = "client_id", type = IdType.INPUT)
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 资源ID
     */
    private String resourceIds;

    /**
     * 作用域
     */
    private String scope;

    /**
     * 授权方式（A,B,C）
     */
    private String authorizedGrantTypes;

    /**
     * 客户端重定向uri
     */
    private String webServerRedirectUri;

    /**
     * 指定用户的权限范围
     */
    private String authorities;
    /**
     * 请求令牌有效时间 设置access_token的有效时间(秒),默认(606012,12小时)
     */
    private Integer accessTokenValidity;

    /**
     * 刷新令牌有效时间 设置refresh_token有效期(秒)，默认(606024*30, 30填)
     */
    private Integer refreshTokenValidity;

    /**
     * 扩展信息 值必须是json格式
     */
    private String additionalInformation;

    /**
     * 是否自动放行 默认false,适用于authorization_code模式,设置用户是否自动approval操作,设置true跳过用户确认授权操作页面，直接跳到redirect_uri
     */
    private String autoapprove;
}
