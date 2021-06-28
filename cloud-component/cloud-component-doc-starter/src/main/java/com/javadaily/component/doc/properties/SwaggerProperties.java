package com.javadaily.component.doc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * <code>SwaggerProperties</code>
 * </p>
 * Description:
 * Swagger属性配置类
 * @author jianzh5
 * @date 2020/3/9 16:48
 */
@Data
@ConfigurationProperties(prefix = "javadaily.swagger")
public class SwaggerProperties {
    /**
     * 是否启用swagger,生产环境建议关闭
     */
    private boolean enabled;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
}
