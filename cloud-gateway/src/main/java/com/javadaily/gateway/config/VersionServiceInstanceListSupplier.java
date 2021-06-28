package com.javadaily.gateway.config;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.RequestDataContext;
import org.springframework.cloud.loadbalancer.core.DelegatingServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Jam
 * @date 2021/6/3 15:47
 * 参考：org.springframework.cloud.loadbalancer.core.ZonePreferenceServiceInstanceListSupplier
 */
@Log4j2
public class VersionServiceInstanceListSupplier extends DelegatingServiceInstanceListSupplier {


    public VersionServiceInstanceListSupplier(ServiceInstanceListSupplier delegate) {
        super(delegate);
    }


    @Override
    public Flux<List<ServiceInstance>> get() {
        return delegate.get();
    }

    @Override
    public Flux<List<ServiceInstance>> get(Request request) {
        return delegate.get(request).map(instances -> filteredByVersion(instances,getVersion(request.getContext())));
    }


    /**
     * filter instance by requestVersion
     * @author jam
     * @date 2021/6/8 10:02
     * @param instances service instances
     * @param requestVersion version
     * @return java.util.List<org.springframework.cloud.client.ServiceInstance>
     */
    private List<ServiceInstance> filteredByVersion(List<ServiceInstance> instances, String requestVersion) {
        log.info("request version is {}",requestVersion);
        if(StringUtils.isEmpty(requestVersion)){
            return instances;
        }

        List<ServiceInstance> filteredInstances = instances.stream()
                .filter(instance -> requestVersion.equalsIgnoreCase(instance.getMetadata().getOrDefault("version","")))
                .collect(Collectors.toList());

        if (filteredInstances.size() > 0) {
            return filteredInstances;
        }

        return instances;
    }

    private String getVersion(Object requestContext) {
        if (requestContext == null) {
            return null;
        }
        String version = null;
        if (requestContext instanceof RequestDataContext) {
            version = getVersionFromHeader((RequestDataContext) requestContext);
        }
        return version;
    }

    /**
     * get version from header
     * @author javadaily
     * @date 2021/6/8 9:50
     * @param context RequestDataContext
     * @return java.lang.String
     */
    private String getVersionFromHeader(RequestDataContext context) {
        if (context.getClientRequest() != null) {
            HttpHeaders headers = context.getClientRequest().getHeaders();
            if (headers != null) {
                // can be extracted from the properties
                return headers.getFirst("version");
            }
        }
        return null;
    }
}

