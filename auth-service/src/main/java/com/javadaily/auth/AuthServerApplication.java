package com.javadaily.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * <code>AuthServerApplication</code>
 * </p>
 * Description:
 * @author jianzh5
 * @date 2020/2/25 16:37
 */
@SpringBootApplication
//对外开启暴露获取token的API接口
@EnableDiscoveryClient
@EnableResourceServer
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @PostMapping("hello")
    public String hello() {
        return "hello";
    }
}
