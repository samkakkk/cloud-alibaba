package com.javadaily.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosConfigProperties.Config;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * <p>
 * <code>NacosRoute</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/8/26 13:47
 */
@Slf4j
@Deprecated
public class NacosRouteDefinitionRepository implements RouteDefinitionRepository {
    private static final String DATA_ID = "cloud-gateway-router.json";
    private static final String GROUP_ID = "DEFAULT_GROUP";

    private List<RouteDefinition> routeDefinitions = Lists.newArrayList();

    private ApplicationEventPublisher publisher;

    private NacosConfigProperties nacosConfigProperties;

    private NacosConfigManager nacosConfigManager;


    public NacosRouteDefinitionRepository(ApplicationEventPublisher publisher, NacosConfigProperties nacosConfigProperties) {
        this.publisher = publisher;
        this.nacosConfigProperties = nacosConfigProperties;
        addListener();
    }

    /**
     * 添加 Nacos监控
     * @author jam
     * @date 2021/5/27 20:11
     */
    private void addListener() {
        try {
            List<Config> configList = nacosConfigProperties.getSharedConfigs();
            String dataId = configList.get(0).getDataId();
            System.out.println(dataId);
            nacosConfigManager.getConfigService().addListener(dataId.substring(0, dataId.lastIndexOf(".")),
                    configList.get(0).getGroup(), new Listener() {
                        @Override
                        public Executor getExecutor() {
                            return null;
                        }

                        @Override
                        public void receiveConfigInfo(String configInfo) {
                            log.info("自动更新配置...\r\n{}",configInfo);
                            publisher.publishEvent(new RefreshRoutesEvent(this));
                        }
                    });
        } catch (NacosException e) {
            log.error("nacos-addListener-error",e);
        }

    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try {
//            String content = nacosConfigProperties.configServiceInstance().getConfig(DATA_ID, GROUP_ID,5000);
//            List<RouteDefinition> routeDefinitions = getListByStr(content);
//            return Flux.fromIterable(routeDefinitions);
            List<Config> configList = nacosConfigProperties.getSharedConfigs();
            String dataId = configList.get(0).getDataId();
            String content = nacosConfigManager.getConfigService()
                    .getConfig(dataId.substring(0,dataId.lastIndexOf(".")), configList.get(0).getGroup(), 5000);
            routeDefinitions = JSONObject.parseArray(content, RouteDefinition.class);
            return Flux.fromIterable(routeDefinitions);

        } catch (NacosException e) {
            log.error("getRouteDefinitions by nacos error", e);
        }
        return Flux.fromIterable(routeDefinitions);
    }


    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }

}
