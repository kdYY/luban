package com.cloud.gateway.limit;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @Author kevins
 * @Description TODO
 * @Date 11:08 上午 2019/9/17
 **/
public class RemoteAddrKeyResolver implements KeyResolver {

    public static final String BEAN_NAME = "remoteAddrKeyResolver";

    /**
     * @return reactor.core.publisher.Mono<java.lang.String>
     * @Author kevins
     * @Description TODO
     * @Date 11:07 上午 2019/9/17
     * @Param [exchange]
     **/
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
    }

}

