package com.javadaily.gateway.filter;

import com.javadaily.base.CloudConstant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <p>
 * <code>GatewayRequestFilter</code>
 * </p>
 * Description:
 * 网关请求过滤器
 *  给请求头添加自定义Header，防止请求绕过网关
 * @author Jam
 * @date 2021/1/25 16:18
 */
@Component
@Order(0)
public class GatewayRequestFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        byte[] token = Base64Utils.encode((CloudConstant.GATEWAY_TOKEN_VALUE).getBytes());
        String[] headerValues = {new String(token)};
        ServerHttpRequest build = exchange.getRequest()
                .mutate()
                .header(CloudConstant.GATEWAY_TOKEN_HEADER, headerValues)
                .build();

        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }

}
