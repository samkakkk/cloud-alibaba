package com.javadaily.component.security.configure;

import com.javadaily.component.security.interceptor.ServerProtectInterceptor;
import com.javadaily.component.security.properties.CloudSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * <code>CloudSecurityInterceptorConfigure</code>
 * </p>
 * Description:
 * 拦截器配置
 * @author Jam
 * @date 2021/1/25 11:23
 */
public class CloudSecurityInterceptorConfigure implements WebMvcConfigurer {

    private CloudSecurityProperties properties;

    @Autowired
    public void setProperties(CloudSecurityProperties properties) {
        this.properties = properties;
    }


    @Bean
    public HandlerInterceptor serverProtectInterceptor() {
        ServerProtectInterceptor interceptor = new ServerProtectInterceptor();
        interceptor.setProperties(properties);
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverProtectInterceptor());
    }
}
