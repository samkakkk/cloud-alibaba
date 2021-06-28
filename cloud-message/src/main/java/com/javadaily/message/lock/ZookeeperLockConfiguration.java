package com.javadaily.message.lock;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.zookeeper.config.CuratorFrameworkFactoryBean;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;

/**
 * <p>
 * <code>RedisLockConfiguration</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/5/14 8:42
 */
@Configuration
public class ZookeeperLockConfiguration {
    @Value("${zookeeper.host}")
    private String zkUrl;


    @Bean
    public CuratorFrameworkFactoryBean curatorFrameworkFactoryBean(){
        return new CuratorFrameworkFactoryBean(zkUrl);
    }

    @Bean
    public ZookeeperLockRegistry zookeeperLockRegistry(CuratorFramework curatorFramework){
        return new ZookeeperLockRegistry(curatorFramework,"/zookeeper-lock");
    }
}
