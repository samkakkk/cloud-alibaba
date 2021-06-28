package com.javadaily.component.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * <code>CloudSecurityProperties</code>
 * </p>
 * Description:
 *
 * @author Jam
 * @date 2021/1/26 17:23
 */
@Data
@ConfigurationProperties(prefix = "javadaily.cloud")
public class CloudSecurityProperties {

    /**
     * 是否只能通过网关获取资源
     * 默认为True
     */
    private Boolean onlyFetchByGateway = Boolean.TRUE;

}
