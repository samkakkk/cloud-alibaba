package com.javadaily.order.client;

import com.javadaily.order.client.factory.AccountClientFallbackFactory;
import com.javadaily.account.api.AccountApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <code>AccountClient</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/11/30 14:50
 */
@Component
@FeignClient(name = "account-service",fallbackFactory = AccountClientFallbackFactory.class)
public interface AccountClient extends AccountApi {

}
