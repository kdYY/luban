package com.cloud.core.constant;

/**
 * @Author kevins
 * @Description 安全常用类
 * @Date 3:07 下午 2019/9/21
 **/
public class SecurityConstant {

    public static final String OAUTH_TOKEN_URL = "/oauth/token";

    public static final String PHONE_KEY = "phone";

    /**
     * sys_oauth_client_details 表的字段 {scrypt}
     */
    public static final String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    public static final String BASE_FIND = "select " + CLIENT_FIELDS + " from sys_oauth_client_details";

    /**
     * 默认的查询语句
     */
    public static final String DEFAULT_FIND = BASE_FIND + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    public static final String DEFAULT_SELECT = BASE_FIND + " where client_id = ?";


    public static final String LOGIN_QQ = "qq";
    public static final String LOGIN_WEIXIN = "weixin";
    public static final String LOGIN_GITEE = "gitee";
    public static final String LOGIN_GITHUB = "github";


}
