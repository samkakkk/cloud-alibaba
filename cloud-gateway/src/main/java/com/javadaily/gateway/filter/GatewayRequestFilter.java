package com.javadaily.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.javadaily.base.CloudConstant;
import com.nimbusds.jose.JWSObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

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
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GatewayRequestFilter implements GlobalFilter {

    private final RedisTemplate<String,String> redisTemplate;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //todo  考虑白名单配置，对一些特殊请求直接放行


        //给Header设置请求头
        byte[] gwToken = Base64Utils.encode((CloudConstant.GATEWAY_TOKEN_VALUE).getBytes());
        String[] headerValues = {new String(gwToken)};
        ServerHttpRequest build = exchange.getRequest()
                .mutate()
                .header(CloudConstant.GATEWAY_TOKEN_HEADER, headerValues)
                .build();

        ServerWebExchange newExchange = exchange.mutate().request(build).build();

        //获取请求头的token
        String headerToken = exchange.getRequest().getHeaders().getFirst(CloudConstant.JWT_HEADER_KEY);

        if (StrUtil.isNotEmpty(headerToken)) {
            // 是否在黑名单
            if(isBlack(headerToken)){
                throw new HttpServerErrorException(HttpStatus.FORBIDDEN,"该令牌已过期，请重新获取令牌");
            }
        }

        //获取请求路径
        String rawPath = exchange.getRequest().getURI().getRawPath();

        if(isPv(rawPath)){
            throw new HttpServerErrorException(HttpStatus.FORBIDDEN,"can't access private API");
        }

        return chain.filter(newExchange);
    }



    /**
     * 判断是否内部私有方法
     * @param requestUri 请求路径
     * @return boolean
     */
    private boolean isPv(String requestUri) {
        return isAccess(requestUri,"/pv");
    }

    /**
     * 通过redis判断token是否为黑名单
     * @param headerToken 请求头
     * @return boolean
     */
    private boolean isBlack(String headerToken) throws ParseException {
        //todo  移除所有oauth2相关代码，暂时使用 OAuth2AccessToken.BEARER_TYPE 代替
        String token  = headerToken.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();

        //解析token
        JWSObject jwsObject = JWSObject.parse(token);
        String payload = jwsObject.getPayload().toString();
        JSONObject jsonObject = JSONUtil.parseObj(payload);

        // JWT唯一标识
        String jti = jsonObject.getStr("jti");
        return redisTemplate.hasKey(CloudConstant.TOKEN_BLACKLIST_PREFIX + jti);
    }


    /**
     * 网关访问控制校验
     */
    private boolean isAccess(String requestURI, String access) {
        //后端标准请求路径为 /访问控制/请求路径
        int index = requestURI.indexOf(access);
//        int i = StringUtils.countOccurrencesOf(requestURI.substring(0,index),"/");
//        log.info("path:{}",i);
        return index >= 0 && StringUtils.countOccurrencesOf(requestURI.substring(0,index),"/") < 1;
    }

}
