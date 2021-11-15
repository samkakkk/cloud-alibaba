package com.javadaily.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * <code>FeignRequestInterceptor</code>
 * </p>
 * Description:
 * 微服务之间feign调用请求头丢失的问题
 * @author jianzh5
 * @date 2020/3/24 9:28
 */
//@Configuration
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {
    /**
     * 微服务之间传递的唯一标识 XID
     */
    public static final String T_REQUEST_ID = "TX_RID";

    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest httpServletRequest =   getHttpServletRequest();
        if(httpServletRequest!=null){
            Map<String, String> headers = getHeaders(httpServletRequest);
            // 传递所有请求头,防止部分丢失
            //此处也可以只传递认证的header
            //requestTemplate.header("Authorization", request.getHeader("Authorization"));
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                template.header(entry.getKey(), entry.getValue());
            }
            // 微服务之间传递的唯一标识,区分大小写所以通过httpServletRequest获取
            if (httpServletRequest.getHeader(T_REQUEST_ID)==null) {
                String sid = String.valueOf(UUID.randomUUID());
                template.header(T_REQUEST_ID, sid);
            }

            log.info("FeignRequestInterceptor:{}", template.toString());
        }
    }


    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取原请求头
     */
    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if(enumeration!=null){
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }
}
