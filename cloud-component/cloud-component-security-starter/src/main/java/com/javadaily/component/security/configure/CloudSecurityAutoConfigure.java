package com.javadaily.component.security.configure;

import com.javadaily.component.security.expression.CustomMethodSecurityExpressionHandler;
import com.javadaily.component.security.handler.CustomAccessDeniedHandler;
import com.javadaily.component.security.handler.CustomAuthenticationEntryPoint;
import com.javadaily.component.security.properties.CloudSecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * <p>
 * <code>CloudSecurityAutoConfigure</code>
 * </p>
 * Description:
 * @author Jam
 * @date 2021/1/26 18:40
 */
@EnableConfigurationProperties(CloudSecurityProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CloudSecurityAutoConfigure extends GlobalMethodSecurityConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public CustomAccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public CustomAuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }


    /**
     * 导入网关拦截器
     * @author jam
     * @date 2021/4/21 15:31
     * @return CloudSecurityInterceptorConfigure
     */
    @Bean
    public CloudSecurityInterceptorConfigure cloudSecurityInterceptorConfigure() {
        return new CloudSecurityInterceptorConfigure();
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new CustomMethodSecurityExpressionHandler();
    }

}
