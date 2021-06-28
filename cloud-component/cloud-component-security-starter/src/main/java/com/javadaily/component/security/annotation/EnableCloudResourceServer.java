package com.javadaily.component.security.annotation;

import com.javadaily.component.security.configure.CloudResourceServerConfigure;
import com.javadaily.component.security.configure.TokenStoreConfigure;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @author Jam
 * 构建Enable注解，用于自定义资源服务器
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableResourceServer //开启资源服务器
@Import({CloudResourceServerConfigure.class, TokenStoreConfigure.class})
public @interface EnableCloudResourceServer {

}
