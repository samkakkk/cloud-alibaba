package com.javadaily.component.security.convert;

import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

/**
 * <p>
 * <code>JwtTokenEnhancer</code>
 * </p>
 * Description:
 * 自定义Token解析
 * @author javadaily
 * @date 2020/7/4 15:56
 */
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter{

    public CustomAccessTokenConverter(){
        super.setUserTokenConverter(new CustomUserAuthenticationConverter());
    }

}
