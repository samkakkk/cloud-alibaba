package com.javadaily.component.security.expression;

import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * <p>
 * <code>MethodSecurityConfig</code>
 * </p>
 * Description:
 * 开启方法校验
 * @author jianzh5
 * @date 2020/8/6 16:07
 */
//@Configuration
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new CustomMethodSecurityExpressionHandler();
    }
}
