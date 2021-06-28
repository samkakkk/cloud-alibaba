package com.javadaily.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * <code>AccountDubboApplication</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/11/30 17:01
 */
@SpringBootApplication
@EnableDubbo
public class AccountDubboApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountDubboApplication.class, args);
    }
}
