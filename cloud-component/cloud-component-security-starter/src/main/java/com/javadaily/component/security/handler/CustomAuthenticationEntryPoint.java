package com.javadaily.component.security.handler;

import com.javadaily.base.ResultData;
import com.javadaily.base.ReturnCode;
import com.javadaily.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * <code>CustomAuthenticationEntryPoint</code>
 * </p>
 * Description:
 * 自定义匿名用户访问无权限资源时的异常
 * @author jianzh5
 * @date 2020/2/29 10:33
 */
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException ex) throws IOException, ServletException {
        String accessToken = request.getHeader("authorization");
        String requestUri = request.getRequestURI();
        log.error("AuthenticationEntryPoint Path is {},access_token is {}",requestUri,accessToken);
        log.error("CustomAuthenticationEntryPoint",ex);
        ResultData<Object> resultData = ResultData.fail(ReturnCode.INVALID_TOKEN.getCode(), ReturnCode.INVALID_TOKEN.getMessage());
        resultData.setData(requestUri);


        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        WebUtils.writeJson(response,resultData);
    }
}
