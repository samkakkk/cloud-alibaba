package com.javadaily.gateway.service;

import com.javadaily.gateway.entity.GatewayLog;
import reactor.core.publisher.Mono;

/**
 * <p>
 * <code>AccessLogService</code>
 * </p>
 * Description:
 *
 * @author Jam
 * @date 2021/3/15 9:40
 */
public interface AccessLogService {

    /**
     * 保存AccessLog
     * @param gatewayLog 请求响应日志
     * @return 响应日志
     */
    Mono<GatewayLog> saveAccessLog(GatewayLog gatewayLog);

}
