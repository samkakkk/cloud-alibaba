package com.javadaily.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * <code>AuthServerApplication</code>
 * </p>
 * Description:
 * @author jianzh5
 * @date 2020/2/25 16:37
 */
@SpringBootApplication
//@EnableBinding({Source.class, Sink.class})
public class MessageServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageServerApplication.class, args);
    }
}
