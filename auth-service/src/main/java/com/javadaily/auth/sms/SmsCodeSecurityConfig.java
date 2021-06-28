package com.javadaily.auth.sms;

import com.javadaily.auth.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <code>SmsCodeAuthenticationSecurityConfig</code>
 * </p>
 * Description:
 * 验证码配置文件
 * @author jianzh5
 * @date 2020/7/13 21:09
 */
@Component
public class SmsCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private SmsCodeAuthenticationSuccessHander successHander;
    @Autowired
    private SmsCodeAuthenticationFailureHander failureHander;
    @Autowired
    private ISysUserService userService;

    /**
     * 短信验证码配置器
     *  所有的配置都可以移步到WebSecurityConfig
     *  builder.authenticationProvider() 相当于 auth.authenticationProvider();
     *  使用外部配置必须要在WebSecurityConfig中用http.apply(smsCodeSecurityConfig)将配置注入进去
     * @param builder
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity builder) throws Exception {
//        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
//        smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
//
//        //成功失败的处理逻辑
//        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(successHander);
//        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(failureHander);


        //注入SmsCodeAuthenticationProvider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserService(userService);

        builder.authenticationProvider(smsCodeAuthenticationProvider);
//                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
