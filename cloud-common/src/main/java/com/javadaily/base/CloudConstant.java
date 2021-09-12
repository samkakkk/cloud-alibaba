package com.javadaily.base;

/**
 * <p>
 * <code>CloudConstant</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/4/1 9:47
 */
public class CloudConstant {
    /**
     * 有效状态
     */
    public static final String VALID_STATUS = "VALID";
    /**
     * 无效状态
     */
    public static final String INVALID_STATUS = "INVALID";

    /**
     * ROLE前綴
     */
    public static final String ROLE_PREFIX = "ROLE_";

    /**
     * JWT签名密钥
     */
    public static final String AUTH_SIGNING_KEY = "java_daily";

    /**
     * 网关保护头
     */
    public static final String GATEWAY_TOKEN_HEADER = "CLOUD_GATEWAY_TOKEN";

    /**
     * 网关保护值
     */
    public static final String GATEWAY_TOKEN_VALUE = "gateway:javadaily";

    /**
     * Jwt请求头
     */
    public static final String JWT_HEADER_KEY = "Authorization";

    /**
     * 无效Token
     */
    public static final String TOKEN_BLACKLIST_PREFIX = "InvalidToken";

    /**
     * Bearer 请求头
     */
    public static final String BEARER_TYPE = "Bearer";
}
