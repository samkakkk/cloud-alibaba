package com.javadaily.component.security.util;

import com.javadaily.component.security.user.SecurityUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 * <code>SecurityUtils</code>
 * </p>
 * Description:
 * oauth2 工具类
 * @author javadaily
 * @date 2020/11/18 9:17
 */
@UtilityClass
public class SecurityUtils {
    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public SecurityUser getUser(){
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    /**
     * 获取当前用户
     * @param authentication 认证信息
     * @return 当前用户
     */
    private static SecurityUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if(principal instanceof SecurityUser){
            return (SecurityUser) principal;
        }
        return null;
    }
}
