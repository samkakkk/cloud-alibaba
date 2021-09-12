package com.javadaily.gateway.oauth2;

import cn.hutool.core.collection.ConcurrentHashSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.util.Set;

/**
 * <p>
 * <code>AccessManager</code>
 * </p>
 * Description:
 * 权限验证管理器
 * @author jianzh5
 * @date 2020/2/28 21:21
 */
@Slf4j
//@Component
@Deprecated
public class AccessManager /*implements ReactiveAuthorizationManager<AuthorizationContext>*/ {
    private final Set<String> permitAll = new ConcurrentHashSet<>();
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();


//    public AccessManager (){
//        permitAll.add("/");
//        permitAll.add("/error");
//        permitAll.add("/favicon.ico");
//        //如果生产环境开启swagger调试
//        permitAll.add("/**/v2/api-docs/**");
//        permitAll.add("/**/swagger-resources/**");
//        permitAll.add("/webjars/**");
//        permitAll.add("/doc.html");
//        permitAll.add("/swagger-ui.html");
//
//        permitAll.add("/**/oauth/**");
//        permitAll.add("/actuator/**");
//        permitAll.add("/blog/**");
//    }

    /**
     * 实现权限验证判断
     */
    /*@Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authenticationMono, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        ServerHttpRequest request = exchange.getRequest();
        //请求资源
        String requestPath = request.getURI().getPath();
        //拼接method
        String methodPath = "["+request.getMethod()+"]" + requestPath;

        // 1. 对应跨域的预检请求直接放行
        if(request.getMethod() == HttpMethod.OPTIONS){
            return Mono.just(new AuthorizationDecision(true));
        }

        // 是否直接放行
        if (permitAll(requestPath)) {
            return Mono.just(new AuthorizationDecision(true));
        }


        return authenticationMono.map(auth -> new AuthorizationDecision(checkAuthorities(auth, methodPath)))
                .defaultIfEmpty(new AuthorizationDecision(false));

    }*/

    /**
     * 校验是否属于静态资源
     * @param requestPath 请求路径
     * @return
     */
   /* private boolean permitAll(String requestPath) {
        return permitAll.stream()
                .filter(r -> ANT_PATH_MATCHER.match(r, requestPath)).findFirst().isPresent();
    }*/


    /**
     * 权限校验
     * @author javadaily
     * @date 2020/8/4 16:47
     * @param auth 用户权限
     * @param requestPath 请求路径
     * @return
     */
   /* private boolean checkAuthorities(Authentication auth, String requestPath) {
        if(auth instanceof OAuth2Authentication){
            OAuth2Authentication authentication = (OAuth2Authentication) auth;
            String clientId = authentication.getOAuth2Request().getClientId();
            log.info("clientId is {}",clientId);
            //用户的权限集合
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

            boolean match = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    //ROLE_开头的为角色，需要过滤掉
                    .filter(item -> !item.startsWith(CloudConstant.ROLE_PREFIX))
                    .anyMatch(permission -> ANT_PATH_MATCHER.match(permission, requestPath));

            log.info("访问权限校验结果:{}",match);

            return match;
        }

        return true;
    }*/



}
