package com.javadaily.gateway.oauth2;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * <code>JdbcAuthenticationManager</code>
 * </p>
 * Description:
 * 自定义授权认证，从JDBC中获取token
 * https://gitee.com/zlt2000/microservices-platform/tree/master/zlt-gateway/sc-gateway 网关参考地址
 * @author jianzh5
 * @date 2020/2/28 20:02
 */
@Slf4j
@Deprecated
public class ReactiveJdbcAuthenticationManager /*implements ReactiveAuthenticationManager*/ {

   /* private TokenStore tokenStore;

    public ReactiveJdbcAuthenticationManager(TokenStore tokenStore){
        this.tokenStore = tokenStore;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap((accessToken ->{
                    log.info("accessToken is :{}",accessToken);
                    OAuth2AccessToken oAuth2AccessToken = this.tokenStore.readAccessToken(accessToken);
                    //根据access_token从数据库获取不到OAuth2AccessToken
                    if(oAuth2AccessToken == null){
                        return Mono.error(new InvalidTokenException("invalid access token,please check"));
                    }else if(oAuth2AccessToken.isExpired()){
                        return Mono.error(new InvalidTokenException("access token has expired,please reacquire token"));
                    }

                    OAuth2Authentication oAuth2Authentication =this.tokenStore.readAuthentication(accessToken);
                    if(oAuth2Authentication == null){
                        return Mono.error(new InvalidTokenException("Access Token 无效!"));
                    }else {
                        return Mono.just(oAuth2Authentication);
                    }
                })).cast(Authentication.class);
    }*/
}
