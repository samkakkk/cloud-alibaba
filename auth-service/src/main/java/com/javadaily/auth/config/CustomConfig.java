package com.javadaily.auth.config;

import com.javadaily.utils.SpringContextHolder;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * <code>CustomConfig</code>
 * </p>
 * Description:
 * Spring 上下文工具
 * @author jianzh5
 * @date 2020/7/23 16:52
 */
@Configuration
@Log4j2
public class CustomConfig {

    @Bean
    @ConditionalOnMissingBean(SpringContextHolder.class)
    public SpringContextHolder springContextHolder() {
        SpringContextHolder holder = new SpringContextHolder();
        log.info("SpringContextHolder [{}]", holder);
        return holder;
    }
}
