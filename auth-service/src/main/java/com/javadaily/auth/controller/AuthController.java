package com.javadaily.auth.controller;

import com.javadaily.base.CloudConstant;
import com.javadaily.base.ResultData;
import com.javadaily.base.ReturnCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author jam
 * @date 2021/8/23 11:13 上午
 */
@RestController
@RequestMapping("/token")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final TokenStore tokenStore;

    private final RedisTemplate<String,String> redisTemplate;

    /**
     * 用户退出登录
     * @param authHeader 从请求头获取token
     */
    @DeleteMapping("/logout")
    public ResultData<String> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader){

        //获取token，去除前缀
        String token = authHeader.replace(OAuth2AccessToken.BEARER_TYPE,"").trim();

        // 解析Token
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);

        //token 已过期
        if(oAuth2AccessToken.isExpired()){
           return ResultData.fail(ReturnCode.INVALID_TOKEN_OR_EXPIRED.getCode(),ReturnCode.INVALID_TOKEN_OR_EXPIRED.getMessage());
        }

        if(StringUtils.isBlank(oAuth2AccessToken.getValue())){
            //访问令牌不合法
            return ResultData.fail(ReturnCode.INVALID_TOKEN.getCode(),ReturnCode.INVALID_TOKEN.getMessage());
        }

        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(oAuth2AccessToken);

        String userName = oAuth2Authentication.getName();

        //获取token唯一标识
        String jti = (String) oAuth2AccessToken.getAdditionalInformation().get("jti");

        //获取过期时间
        Date expiration = oAuth2AccessToken.getExpiration();
        long exp = expiration.getTime() / 1000;

        long currentTimeSeconds = System.currentTimeMillis() / 1000;

        //设置token过期时间
        redisTemplate.opsForValue().set(CloudConstant.TOKEN_BLACKLIST_PREFIX + jti, userName, (exp - currentTimeSeconds), TimeUnit.SECONDS);

        return ResultData.success("退出成功");
    }

}
