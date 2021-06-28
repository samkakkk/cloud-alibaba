package com.javadaily.gateway.service.impl;

import com.javadaily.gateway.entity.GatewayLog;
import com.javadaily.gateway.repository.AccessLogRepository;
import com.javadaily.gateway.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * <p>
 * <code>AccessLogServiceImpl</code>
 * </p>
 * Description:
 *
 * @author Jam
 * @date 2021/3/15 9:52
 */
@Service
public class AccessLogServiceImpl implements AccessLogService {
    @Autowired
    private AccessLogRepository accessLogRepository;

    @Override
    public Mono<GatewayLog> saveAccessLog(GatewayLog gatewayLog) {
        return accessLogRepository.insert(gatewayLog);
//        return mongoTemplate.save(gatewayLog,"testLog");
    }
}
