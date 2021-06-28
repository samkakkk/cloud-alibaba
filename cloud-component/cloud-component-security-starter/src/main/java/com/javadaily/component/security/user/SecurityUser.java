package com.javadaily.component.security.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * <p>
 * <code>CustomUser</code>
 * </p>
 * Description:
 * 自定义用户信息
 * @author jianzh5
 * @date 2020/11/17 15:05
 */
public class SecurityUser extends User {
    @Getter
    private Integer id;

    @Getter
    private String mobile;

    public SecurityUser(Integer id, String mobile,
                        String username, String password,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", username='" + super.getUsername() + '\'' +
                '}';
    }
}
