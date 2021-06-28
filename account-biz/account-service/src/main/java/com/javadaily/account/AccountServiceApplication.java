package com.javadaily.account;

import com.javadaily.component.security.annotation.EnableCloudResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author JAVA日知录
 * @date 2019/11/30 23:44
 */
//@SpringBootApplication(scanBasePackages={"com.javadaily"})
@EnableDiscoveryClient
@SpringCloudApplication
//@EnableBinding({Sink.class})//接收消息
@EnableCloudResourceServer
public class AccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
