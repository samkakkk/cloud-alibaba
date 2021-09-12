package com.javadaily.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

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
@Deprecated
public class SecurityConfig {

   /* @Autowired
    private DataSource dataSource;
    @Autowired
    private AccessManager accessManager;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;
*/

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception{
/*
        //jwt token管理器
        ReactiveAuthenticationManager tokenAuthenticationManager = new ReactiveJwtAuthenticationManager(tokenStore());
        //认证过滤器
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(tokenAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());
*/

        http
                .httpBasic().disable()
                .csrf().disable();

        return http.build();
    }

/*
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
*/

}
