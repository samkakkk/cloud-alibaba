package com.javadaily.config;

import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@Slf4j
//@ConditionalOnClass(Retryer.class)
@ConditionalOnClass(Feign.class)
public class OpenFeignConfig {

    /**
     * 微服务之间传递的唯一标识 XID
     */
    public static final String T_REQUEST_ID = "TX_RID";

    @Autowired
    private ObjectFactory<HttpMessageConverters> objectFactory;

    /**
     * 给Feign设置请求头
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if(null != attributes){
                HttpServletRequest request = attributes.getRequest();
                Map<String, String> headers = getRequestHeaders(request);

                // 传递所有请求头,防止部分丢失
                //此处也可以只传递认证的header
                //requestTemplate.header("Authorization", request.getHeader("Authorization"));
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    requestTemplate.header(entry.getKey(), entry.getValue());
                }

                // 微服务之间传递的唯一标识,区分大小写所以通过httpServletRequest获取
                if (request.getHeader(T_REQUEST_ID)==null) {
                    String sid = String.valueOf(UUID.randomUUID());
                    requestTemplate.header(T_REQUEST_ID, sid);
                }


                log.debug("FeignRequestInterceptor:{}", requestTemplate.toString());
            }
        };
    }


    /**
     * 自定义异常解码器
     * @return OpenFeignErrorDecoder
     */
    @Bean
    public ErrorDecoder errorDecoder(){
        return new OpenFeignErrorDecoder();
    }



    /**
     * 日志级别
     */
    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }

    /**
     * 不重试
     */
    @Bean
    Retryer retryer(){
        return Retryer.NEVER_RETRY;
    }




//    @Bean
//    Decoder decoder(){
//        return new ExceptionFeignDecoder(objectFactory);
//    }



    /**
     * 获取请求头
     */
    private Map<String, String> getRequestHeaders(HttpServletRequest request) {
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
