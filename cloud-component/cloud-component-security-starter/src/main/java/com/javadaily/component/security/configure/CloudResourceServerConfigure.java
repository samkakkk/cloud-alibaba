package com.javadaily.component.security.configure;

import com.javadaily.component.security.convert.CustomUserAuthenticationConverter;
import com.javadaily.component.security.handler.CustomAccessDeniedHandler;
import com.javadaily.component.security.handler.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

/**
 * <p>
 * <code>CloudResourceServerConfigure</code>
 * </p>
 * Description:
 * 模块化资源服务器配置
 * @author Jam
 * @date 2021/2/2 14:43
 */
public class CloudResourceServerConfigure extends ResourceServerConfigurerAdapter {
    private CustomAccessDeniedHandler accessDeniedHandler;
    private CustomAuthenticationEntryPoint exceptionEntryPoint;

    private TokenStore tokenStore;

    @Value("${security.oauth2.resource.id}")
    private String resourceId ;

    @Autowired(required = false)
    public void setAccessDeniedHandler(CustomAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired(required = false)
    public void setExceptionEntryPoint(CustomAuthenticationEntryPoint exceptionEntryPoint) {
        this.exceptionEntryPoint = exceptionEntryPoint;
    }

    @Autowired(required = false)
    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers(
                        "/v2/api-docs/**",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userTokenConverter = new CustomUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userTokenConverter);

        if (exceptionEntryPoint != null) {
            resources.authenticationEntryPoint(exceptionEntryPoint);
        }
        if (accessDeniedHandler != null) {
            resources.accessDeniedHandler(accessDeniedHandler);
        }

        resources.resourceId(resourceId).tokenStore(tokenStore);
    }

}
