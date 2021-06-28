package com.javadaily.auth.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * <code>MethodGrantedAuthority</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2021/1/12 16:43
 */
@Data
@Deprecated
public class MethodGrantedAuthority implements GrantedAuthority {

    private String method;
    private String url;

    public MethodGrantedAuthority(String method, String url){
        this.method = method;
        this.url = url;
    }

    @Override
    public String getAuthority() {
        return "["+method+"]" + url;
    }
}
