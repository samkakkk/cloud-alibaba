package com.javadaily.auth.security;

import com.javadaily.component.security.user.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <code>JwtTokenEnhancer</code>
 * </p>
 * Description:
 * 自定义Token增强
 * @author jianzh5
 * @date 2020/7/4 15:56
 */
public class CustomJwtTokenConverter extends JwtAccessTokenConverter{

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getUserAuthentication().getPrincipal();
        final Map<String,Object> additionalInformation = new HashMap<>(4);
        additionalInformation.put("userId", securityUser.getId());
        additionalInformation.put("mobile", securityUser.getMobile());
        additionalInformation.put("author","java日知录");
        additionalInformation.put("weixin","javadaily");
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInformation);
        return super.enhance(oAuth2AccessToken,authentication);
    }

}
