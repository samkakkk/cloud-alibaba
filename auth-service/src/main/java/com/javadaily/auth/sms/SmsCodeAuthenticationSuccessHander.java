package com.javadaily.auth.sms;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * <code>SmsCodeAuthenticationSuccessHander</code>
 * </p>
 * Description:
 * 登陆成功认证
 * @author jianzh5
 * @date 2020/7/13 20:57
 */
@Slf4j
@Component
public class SmsCodeAuthenticationSuccessHander implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("sms code login success");
        log.info("authentication is {},authorities: {}",authentication.getPrincipal(),authentication.getAuthorities());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(authentication));
    }
}
