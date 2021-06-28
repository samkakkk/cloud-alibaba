package com.javadaily.component.security.interceptor;


import com.javadaily.base.CloudConstant;
import com.javadaily.base.ResultData;
import com.javadaily.component.security.properties.CloudSecurityProperties;
import com.javadaily.utils.WebUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * <code>ServerProtectInterceptor</code>
 * </p>
 * Description:
 * 网关请求拦截器
 * @author Jam
 * @date 2021/1/21 16:45
 */
public class ServerProtectInterceptor implements HandlerInterceptor {

    private CloudSecurityProperties properties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler){

        if (!properties.getOnlyFetchByGateway()) {
            return true;
        }

        String token = request.getHeader(CloudConstant.GATEWAY_TOKEN_HEADER);

        String gatewayToken = new String(Base64Utils.encode(CloudConstant.GATEWAY_TOKEN_VALUE.getBytes()));

        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            ResultData<String> resultData = new ResultData<>();
            resultData.setSuccess(false);
            resultData.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resultData.setMessage("请通过网关访问资源");
            WebUtils.writeJson(response,resultData);
            return false;
        }
    }

    public void setProperties(CloudSecurityProperties properties) {
        this.properties = properties;
    }
}
