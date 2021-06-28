package com.javadaily.component.security.convert;

import com.javadaily.component.security.user.SecurityUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * <code>CustomTokenConverter</code>
 * </p>
 * Description:
 * 自定义Token解析
 * @author javadaily
 * @date 2020/7/4 15:56
 */
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    /**
     * 重写抽取认证信息
     * @author javadaily
     * @date 2020/11/18 10:56
     * @param map 用户认证信息
     * @return Authentication
     */
    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);

            String username = (String) map.get(USERNAME);
            Integer id = (Integer) map.get("userId");
            String mobile  = (String) map.get("mobile");
            SecurityUser user = new SecurityUser(id, mobile, username,"N/A", authorities);
            return new UsernamePasswordAuthenticationToken(user, "N/A", authorities);
        }
        return null;
    }


    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }



}
