package com.javadaily.gateway.config;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Description:
 * 自定义灰度
 * 通过给请求头添加Version 与 Service Instance 元数据属性进行对比
 * @author Jam
 * @date 2021/6/1 17:26
 */
@Log4j2
public class VersionGrayLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private final ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
    private final String serviceId;

    private final AtomicInteger position;

    public VersionGrayLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
        this(serviceInstanceListSupplierProvider,serviceId,new Random().nextInt(1000));
    }

    public VersionGrayLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider,
                                   String serviceId, int seedPosition) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
        this.position = new AtomicInteger(seedPosition);
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {

        ServiceInstanceListSupplier supplier = this.serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);

        return supplier.get(request).next()
                .map(serviceInstances -> processInstanceResponse(serviceInstances,request));

    }


    private Response<ServiceInstance> processInstanceResponse(List<ServiceInstance> instances, Request request) {
        if (instances.isEmpty()) {
            log.warn("No servers available for service: " + this.serviceId);
            return new EmptyResponse();
        } else {
            DefaultRequestContext requestContext = (DefaultRequestContext) request.getContext();
            RequestData clientRequest = (RequestData) requestContext.getClientRequest();
            HttpHeaders headers = clientRequest.getHeaders();

            // get Request Header
            String reqVersion = headers.getFirst("version");

            if(StringUtils.isEmpty(reqVersion)){
                return processRibbonInstanceResponse(instances);
            }

            log.info("request header version : {}",reqVersion );

            List<ServiceInstance> serviceInstances = instances.stream()
                    .filter(instance -> reqVersion.equals(instance.getMetadata().get("version")))
                    .collect(Collectors.toList());

            if(serviceInstances.size() > 0){
//                return new DefaultResponse(serviceInstances.get(0));
                return processRibbonInstanceResponse(serviceInstances);
            }else{
                return processRibbonInstanceResponse(instances);
            }

            //filter Service Instance
//            Optional<ServiceInstance> instanceOptional = instances.stream().filter(instance -> {
//                //get metadata
//                Map<String,String> metadata = instance.getMetadata();
//                String targetVersion = MapUtil.getStr(metadata, "version");
//                return reqVersion.equals(targetVersion);
//            }).findFirst();
//
//            if(instanceOptional.isPresent()){
//                return new DefaultResponse(instanceOptional.get());
//            }else {
//                return processRibbonInstanceResponse(instances);
//            }


        }
    }

    /**
     * 默认负载均衡器
     * 参考 org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer#getInstanceResponse
     * @author javadaily
     * @date 2021/6/7 13:47
     * @param instances Service instance
     * @return org.springframework.cloud.client.loadbalancer.Response<org.springframework.cloud.client.ServiceInstance>
     */
    private Response<ServiceInstance> processRibbonInstanceResponse(List<ServiceInstance> instances) {

        int pos = Math.abs(this.position.incrementAndGet());

        ServiceInstance instance = instances.get(pos % instances.size());

        return new DefaultResponse(instance);
    }


}
