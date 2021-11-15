package com.javadaily.order.client.factory;

import com.javadaily.order.client.fallback.AccountClientFallback;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <code>AccountFeignFallbackFactory</code>
 * </p>
 * Description:
 * 熔断配置类
 * @author jianzh5
 * @date 2020/10/13 15:43
 */
@Component
public class AccountClientFallbackFactory implements FallbackFactory<AccountClientFallback> {

    @Override
    public AccountClientFallback create(Throwable throwable) {

        AccountClientFallback accountClientFallback = new AccountClientFallback();
        accountClientFallback.setThrowable(throwable);

        return accountClientFallback;
    }

}
