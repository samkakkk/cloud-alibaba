package com.javadaily.order.client.factory;

import com.javadaily.order.client.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <code>ProductClientFallbackFactory</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/10/13 15:43
 */
@Component
public class ProductClientFallbackFactory implements FallbackFactory<ProductClientFallback> {

    @Override
    public ProductClientFallback create(Throwable throwable) {
        return new ProductClientFallback(throwable);
    }

}
