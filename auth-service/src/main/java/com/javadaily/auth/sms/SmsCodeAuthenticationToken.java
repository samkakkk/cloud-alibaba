package com.javadaily.auth.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * <p>
 * <code>SmsAuthenticationToken</code>
 * </p>
 * Description:
 * 实现手机号登录，参考org.springframework.security.authentication.UsernamePasswordAuthenticationToken
 * 参考连接：
 * https://cloud.tencent.com/developer/article/1040105
 * @author javadaily
 * @date 2020/7/13 8:44
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 520L;

    /**
     * 账号主体信息，手机号验证码登录体系中代表 手机号码
     */
    private final Object principal;


    /**
     * 构建未授权的 SmsCodeAuthenticationToken
     * @param mobile 手机号码
     */
    public SmsCodeAuthenticationToken(String mobile) {
        super(null);
        this.principal = mobile;
        setAuthenticated(false);
    }


    /**
     * 构建已经授权的 SmsCodeAuthenticationToken
     */
    public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }


    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        if(isAuthenticated){
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }else{
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
