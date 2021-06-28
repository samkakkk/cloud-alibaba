package com.javadaily.gateway.config;

import com.javadaily.base.CloudConstant;
import com.javadaily.gateway.oauth2.AccessDeniedHandler;
import com.javadaily.gateway.oauth2.AccessManager;
import com.javadaily.gateway.oauth2.AuthenticationEntryPointHandler;
import com.javadaily.gateway.oauth2.ReactiveJwtAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

import javax.sql.DataSource;

/**
 * <p>
 * <code>SecurityConfig</code>
 * </p>
 * https://www.docs4dev.com/docs/zh/spring-security/5.1.2.RELEASE/reference/webflux-oauth2.html
 * https://dev.to/toojannarong/spring-security-with-jwt-the-easiest-way-2i43
 * Description:
 * @author jianzh5
 * @date 2020/2/28 15:50
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private AccessManager accessManager;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;


    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception{
        //token管理器
        //ReactiveAuthenticationManager tokenAuthenticationManager = new ReactiveJdbcAuthenticationManager(new JdbcTokenStore(dataSource));

        //也可以写成
        ReactiveAuthenticationManager tokenAuthenticationManager = new ReactiveJwtAuthenticationManager(tokenStore());
//
        //认证过滤器
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(tokenAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());

        http
                .httpBasic().disable()
                .csrf().disable();
//                .authorizeExchange()
//                .pathMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyExchange().access(accessManager)
//                //异常处理器
//                .and()
//                    .exceptionHandling()
//                        .accessDeniedHandler(accessDeniedHandler)
//                        .authenticationEntryPoint(authenticationEntryPointHandler)
//                .and()
//                // 跨域过滤器
////                .addFilterAt(corsFilter(), SecurityWebFiltersOrder.CORS)
//                //oauth2认证过滤器
//                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }


//    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }


//    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer(){
        JwtAccessTokenConverter jwtTokenEnhancer = new JwtAccessTokenConverter();
        jwtTokenEnhancer.setSigningKey(CloudConstant.AUTH_SIGNING_KEY);
        return jwtTokenEnhancer;
    }


}
