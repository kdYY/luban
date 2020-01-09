package com.cloud.auth.security;

import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ClientDetailsService
 * @Description: TODO
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
public class ClientDetailsService extends JdbcClientDetailsService {
    private static final Map<String, ClientDetails> CLIENTS = new HashMap<>();

    public static void clearCache() {
        CLIENTS.clear();
    }

    public static void clearCache(String clientId) {
        CLIENTS.remove(clientId);
    }

    public ClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 重写原生方法支持缓存
     *
     * @param clientId
     * @return
     * @throws InvalidClientException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = CLIENTS.get(clientId);
        if (clientDetails != null) {
            return clientDetails;
        }
        ClientDetails clientDetails1 = super.loadClientByClientId(clientId);
        CLIENTS.put(clientId, clientDetails1);
        return clientDetails1;
    }
}
