package com.javadaily.gateway.oauth2;

import lombok.extern.slf4j.Slf4j;

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
//@Component
@Deprecated
public class AccessDeniedHandler /*implements ServerAccessDeniedHandler*/ {

   /* @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException ex) {
        //获取请求路径
        String path = exchange.getRequest().getURI().getPath();
        ResultData<String> resultData = ResultData.fail(ReturnCode.RC403.getCode(), ReturnCode.RC403.getMessage());
        resultData.setData(path);

        return Mono.defer(() -> Mono.just(exchange.getResponse())).flatMap((response) -> {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer buffer = dataBufferFactory.wrap(JSONObject.toJSONString(resultData).getBytes(Charset.defaultCharset()));
            return response.writeWith(Mono.just(buffer)).doOnError((error) -> {
                DataBufferUtils.release(buffer);
            });
        });
    }*/
}
