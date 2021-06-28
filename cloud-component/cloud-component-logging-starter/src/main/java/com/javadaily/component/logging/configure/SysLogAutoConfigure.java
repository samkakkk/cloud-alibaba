package com.javadaily.component.logging.configure;

import com.javadaily.component.logging.aspect.SysLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jam
 */
@Configuration
public class SysLogAutoConfigure {

    @Bean
    public SysLogAspect controllerLogAspect(){
        return new SysLogAspect();
    }

}
