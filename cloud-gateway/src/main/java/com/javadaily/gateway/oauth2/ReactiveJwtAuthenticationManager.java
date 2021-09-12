package com.javadaily.gateway.oauth2;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * <code>ReactiveJwtAuthenticationManager</code>
 * </p>
 * Description:
 * 自定义JWT Token认证
 * @author jianzh5
 * @date 2020/7/5 17:56
 */
@Slf4j
@Deprecated
public class ReactiveJwtAuthenticationManager /*implements ReactiveAuthenticationManager*/ {
    /*private TokenStore tokenStore;

    public ReactiveJwtAuthenticationManager(TokenStore tokenStore){
        this.tokenStore = tokenStore;
    }


    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        //String token = authentication.getCredentials().toString();

        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap((accessToken ->{
                    log.info("accessToken is :{}",accessToken);
                    OAuth2AccessToken oAuth2AccessToken = this.tokenStore.readAccessToken(accessToken);
                    //根据access_token从JWT获取不到OAuth2AccessToken
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
                })).cast(Authentication.class)
                ;
    }*/
}
