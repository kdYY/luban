package com.cloud.auth.token;

import com.cloud.auth.service.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.oauth2.provider.token.UserAuthenticationConverter.USERNAME;

/**
 * @ClassName CustomJwtAccessTokenConverter
 * @Description: 自定义JwtAccessToken转换器 用于扩展token
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {
    /**
     * token增强器
     *
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (accessToken instanceof DefaultOAuth2AccessToken) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SecurityUser) {
                SecurityUser user = (SecurityUser) principal;
                HashMap<String, Object> map = new HashMap<>();
                map.put(USERNAME, user.getUsername());
                map.put("userId", user.getUserId());
                map.put("identificationNumber", user.getIdentificationNumber());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
            }
        }
        return super.enhance(accessToken, authentication);
    }


    /**
     * 主要是资源服务器解析时一定要有bearer这个头才认为是一个oauth请求
     *
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        return super.encode(accessToken, authentication);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userAuthenticationConverter = new UserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        return accessTokenConverter.extractAuthentication(map);

    }
}
