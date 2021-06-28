package com.javadaily.order;

import com.javadaily.component.security.annotation.EnableCloudResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>
 * <code>OrderServiceApplication</code>
 * </p>
 * @author JAVA日知录
 * Description:
 * 订单服务启动类
 */
@SpringBootApplication/*(scanBasePackages = {"com.javadaily.product","com.javadaily.order"})*/
@EnableDiscoveryClient
@EnableFeignClients/*(basePackages = "com.javadaily.account.*")*/
@EnableCloudResourceServer
//@EnableBinding({Source.class}) //发送消息
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
