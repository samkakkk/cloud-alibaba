package com.javadaily.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadaily.base.Body;
import com.javadaily.base.ResultData;
import com.javadaily.config.OpenFeignConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * @author javadaily
 * swagger相当于是寄宿在应用程序中的一个web服务，统一响应处理器拦截了应用所有的响应，对swagger-ui的响应产生了影响。
 * 解决集成Swagger出现404问题，配置统一响应处理器拦截的范围，只拦截本项目的Controller类
 * @date 2021/7/8 10:10 上午
 */
@RestControllerAdvice(basePackages = "com.javadaily")
@Slf4j
public class BaseResponseAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

    private final Class<? extends Annotation> ANNOTATION_TYPE = Body.class;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

//        Annotation annotation = methodParameter.getContainingClass().getAnnotation(ANNOTATION_TYPE);
//        boolean b1 = methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
//        boolean b = Objects.nonNull(methodParameter.getContainingClass().getAnnotation(ANNOTATION_TYPE)) || (methodParameter.hasMethodAnnotation(ANNOTATION_TYPE));

        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        //Feign请求时通过拦截器设置请求头，如果是Feign请求则直接返回实体对象
        boolean isFeign = serverHttpRequest.getHeaders().containsKey(OpenFeignConfig.T_REQUEST_ID);

        if(isFeign){
            return object;
        }

        if(object instanceof String){
            return objectMapper.writeValueAsString(ResultData.success(object));
        }

        if(object instanceof ResultData){
            return object;
        }

        return ResultData.success(object);
    }

}
