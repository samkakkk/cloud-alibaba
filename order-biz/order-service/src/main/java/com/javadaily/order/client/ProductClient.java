package com.javadaily.order.client;

import com.javadaily.account.api.ProductApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <code>ProductClient</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/11/30 14:50
 */
@Component
@FeignClient(name = "product-service"/*,fallbackFactory = ProductClientFallbackFactory.class*/)
public interface ProductClient extends ProductApi {
}
