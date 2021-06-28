package com.javadaily.component.security.expression;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Set;

/**
 * <p>
 * <code>CustomMethodSecurityExpressionRoot</code>
 * </p>
 * Description:
 * 自定义权限校验
 * @author jianzh5
 * @date 2020/8/6 16:01
 */
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    private Object filterObject;
    private Object returnObject;


    public boolean hasPrivilege(String permission){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        //参考org.springframework.security.access.expression.SecurityExpressionRoot.hasAnyAuthority()实现，简单高效
        Set<String> roleSet = AuthorityUtils.authorityListToSet(authorities);
        return roleSet.contains(permission);

//        return authorities.stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .filter(item -> !item.startsWith(CloudConstant.ROLE_PREFIX))
////                    .anyMatch(x -> ANT_PATH_MATCHER.match(x, permission));
//                    .anyMatch(x -> PatternMatchUtils.simpleMatch(permission, x));
    }


    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }
}
