package com.javadaily.component.security.handler;

import com.javadaily.base.ResultData;
import com.javadaily.base.ReturnCode;
import com.javadaily.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * <code>CustomDeniedHandler</code>
 * </p>
 * Description:
 * 认证过的用户访问无权限资源时的异常
 * @author jianzh5
 * @date 2020/2/29 10:43
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) {
        String requestUri = request.getRequestURI();
        log.error("access denied path,{}",requestUri);
        log.error("CustomDeniedHandler",ex);
        ResultData<String> resultData = ResultData.fail(ReturnCode.ACCESS_DENIED.getCode(), ReturnCode.ACCESS_DENIED.getMessage());
        resultData.setData(requestUri);

        WebUtils.writeJson(response,resultData);
    }
}
