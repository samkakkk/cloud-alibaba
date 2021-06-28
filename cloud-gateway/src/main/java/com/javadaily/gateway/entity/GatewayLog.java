package com.javadaily.gateway.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * <p>
 * <code>GatewayLog</code>
 * </p>
 * Description:
 * 请求日志实体
 * @author Jam
 * @date 2021/3/10 17:29
 */
@Data
@Document
public class GatewayLog {
    @Id
    private String id;

    /**访问实例*/
    private String targetServer;

    /**请求路径*/
    private String requestPath;

    /**请求方法*/
    private String requestMethod;

    /**协议 */
    private String schema;

    /**请求体*/
    private String requestBody;

    /**响应体*/
    private String responseData;

    /**请求ip*/
    private String ip;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date requestTime;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date responseTime;

    /**执行时间*/
    private long executeTime;

}
