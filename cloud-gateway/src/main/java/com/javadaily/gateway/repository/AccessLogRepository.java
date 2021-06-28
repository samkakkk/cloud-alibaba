package com.javadaily.gateway.repository;

import com.javadaily.gateway.entity.GatewayLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * <code>AccessLogRepository</code>
 * </p>
 * Description:
 *
 * @author Jam
 * @date 2021/3/15 9:48
 */
@Repository
public interface AccessLogRepository extends ReactiveMongoRepository<GatewayLog,String> {

}
