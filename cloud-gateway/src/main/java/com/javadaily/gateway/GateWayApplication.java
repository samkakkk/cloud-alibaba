package com.javadaily.gateway;

import com.javadaily.gateway.config.VersionServiceInstanceListSupplierConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;

/**
 * <p>
 * <code>Application</code>
 * </p>
 * SpringCloud Gateway
 * Description:
 * @author jianzh5
 * @date 2020/1/9 19:35
 */
@SpringBootApplication
@EnableDiscoveryClient
//@LoadBalancerClient(value = "auth-service", configuration = CustomLoadBalancerConfiguration.class)
//@LoadBalancerClients(defaultConfiguration = VersionLoadBalancerConfiguration.class)
@LoadBalancerClients(defaultConfiguration = VersionServiceInstanceListSupplierConfiguration.class)
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
