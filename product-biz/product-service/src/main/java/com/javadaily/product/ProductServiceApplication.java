package com.javadaily.product;

import com.javadaily.component.security.annotation.EnableCloudResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * <p>
 * <code>ProductServiceApplication</code>
 * </p>
 * @author JAVA日知录
 * @date 2019/11/30 23:44
 */
@SpringCloudApplication
@EnableCloudResourceServer
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
