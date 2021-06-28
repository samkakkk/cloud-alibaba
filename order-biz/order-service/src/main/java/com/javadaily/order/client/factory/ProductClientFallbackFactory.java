package com.javadaily.order.client.factory;

import com.javadaily.order.client.ProductClient;
import com.javadaily.order.client.fallback.ProductClientFallback;
import feign.hystrix.FallbackFactory;
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
public class ProductClientFallbackFactory implements FallbackFactory<ProductClient> {

    @Override
    public ProductClient create(Throwable throwable) {
        ProductClientFallback productClientFallback = new ProductClientFallback();
        productClientFallback.setCause(throwable);
        return productClientFallback;
    }

}
