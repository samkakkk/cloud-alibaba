package com.javadaily.gateway.oauth2;

import com.alibaba.fastjson.JSONObject;
import com.javadaily.base.ResultData;
import com.javadaily.base.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * <p>
 * <code>AccessDeniedHandler</code>
 * </p>
 * Description:
 * 网关异常处理器
 * @author jianzh5
 * @date 2020/2/29 11:26
 */
@Slf4j
@Component
public class AuthenticationEntryPointHandler implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        //获取请求路径
        String path = exchange.getRequest().getURI().getPath();
        log.error("Authentication error path is {}, error type is {}",path,ex.getClass().getSuperclass().getName());
        //todo 需要细化异常分类，不能统一返回
        ResultData<String> resultData = ResultData.fail(ReturnCode.RC401.getCode(), ReturnCode.RC401.getMessage());
        resultData.setData(path);

        return Mono.defer(() -> Mono.just(exchange.getResponse())).flatMap((response) -> {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer buffer = dataBufferFactory.wrap(JSONObject.toJSONString(resultData).getBytes(Charset.defaultCharset()));
            return response.writeWith(Mono.just(buffer)).doOnError((error) -> {
                DataBufferUtils.release(buffer);
            });
        });
    }
}
