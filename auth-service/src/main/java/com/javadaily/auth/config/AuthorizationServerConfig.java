package com.javadaily.auth.config;

import com.javadaily.auth.filter.CustomClientCredentialsTokenEndpointFilter;
import com.javadaily.auth.security.CustomJwtTokenConverter;
import com.javadaily.auth.service.impl.UserDetailServiceImpl;
import com.javadaily.auth.translator.CustomWebResponseExceptionTranslator;
import com.javadaily.base.CloudConstant;
import com.javadaily.base.ResultData;
import com.javadaily.base.ReturnCode;
import com.javadaily.utils.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

/**
 * <p>
 * <code>AuthorizationServerConfig</code>
 * </p>
 * Description:
 * 认证服务器配置
 * @author jianzh5
 * @date 2020/2/26 16:26
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 自定义用户实现
     */
    private final UserDetailServiceImpl userDetailService;
    /**
     * 认证管理器
     */
    private final AuthenticationManager authenticationManager;
    /**
     * 注入数据源，client信息存放在数据库
     */
    private final DataSource dataSource;


    @Autowired
    private  TokenGranter tokenGranter;


    /**
     * access_token存储器
     * 这里存储在数据库，大家可以结合自己的业务场景考虑将access_token存入数据库还是redis
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }


    /**
     * JwtAccessTokenConverter
     * TokenEnhancer的子类，帮助程序在JWT编码的令牌值和OAuth身份验证信息之间进行转换。
     */
    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer(){
        //自定义jwt 输出内容，若不需要就直接使用JwtAccessTokenConverter
        JwtAccessTokenConverter converter = new CustomJwtTokenConverter();
        // 设置对称签名
        converter.setSigningKey(CloudConstant.AUTH_SIGNING_KEY);
        return converter;
    }



    /**
     * 使用了自定义的TokenGranterConfig后就不需要再注入tokenServices了
     * 修改token配置
     * @return
     */
//    @Primary
//    @Bean
//    public DefaultTokenServices tokenServices(){
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenEnhancer(jwtTokenEnhancer());
//        tokenServices.setTokenStore(tokenStore());
//        tokenServices.setSupportRefreshToken(true);
//        //设置token有效期，默认12小时，此处修改为6小时   21600
//        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 6);
//        //设置refresh_token的有效期，默认30天，此处修改为7天 604800
//        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
//        return tokenServices;
//    }


    /**
     * 从数据库读取clientDetails相关配置
     * 有InMemoryClientDetailsService 和 JdbcClientDetailsService 两种方式选择
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 注入密码加密实现器
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证服务器Endpoints配置
     * 如果使用了自定义的短信验证码授权模式这里只需要保留endpoints.tokenGranter()即可！
     * 其他部分代码都可清理
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //如果需要使用refresh_token模式则需要注入userDetailService
        endpoints
                .authenticationManager(this.authenticationManager)
                .userDetailsService(userDetailService)
//                注入tokenGranter
                .tokenGranter(tokenGranter);
                //注入自定义的tokenservice，如果不使用自定义的tokenService那么就需要将tokenServce里的配置移到这里
//                .tokenServices(tokenServices());

        // 自定义异常转换类
        endpoints.exceptionTranslator(new CustomWebResponseExceptionTranslator());
    }


    /**
     * 自定义认证异常响应数据
     * @return
     */


    /**
     * 认证服务器相关接口权限管理
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //重写客户端异常
        CustomClientCredentialsTokenEndpointFilter endpointFilter = new CustomClientCredentialsTokenEndpointFilter(security);
        endpointFilter.afterPropertiesSet();
        endpointFilter.setAuthenticationEntryPoint(authenticationEntryPoint());
        security.addTokenEndpointAuthenticationFilter(endpointFilter);

        security
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
                /*.allowFormAuthenticationForClients()*/ //如果使用表单认证则需要加上
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, e) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ResultData<String> resultData = ResultData.fail(ReturnCode.INVALID_TOKEN_OR_EXPIRED.getCode(), ReturnCode.INVALID_TOKEN_OR_EXPIRED.getMessage());
            WebUtils.writeJson(response,resultData);
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            //response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            //response.setHeader("Access-Control-Allow-Origin", "*");
            //response.setHeader("Cache-Control", "no-cache");
            ResultData<String> resultData = ResultData.fail(ReturnCode.CLIENT_AUTHENTICATION_FAILED.getCode(), ReturnCode.CLIENT_AUTHENTICATION_FAILED.getMessage());
            WebUtils.writeJson(response,resultData);
        };
    }

    /**
     * client存储方式，此处使用jdbc存储
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }

}
