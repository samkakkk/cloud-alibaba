package com.javadaily.auth.sms;

import com.javadaily.auth.po.SysUser;
import com.javadaily.auth.service.ISysUserService;
import com.javadaily.base.CloudConstant;
import com.javadaily.component.security.user.SecurityUser;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * <code>SmsAuthenticationProvider</code>
 * </p>
 * Description:
 * 短信登陆鉴权 Provider，要求实现 AuthenticationProvider 接口
 * @author jianzh5
 * @date 2020/7/13 13:07
 */
@Log4j2
public class SmsCodeAuthenticationProvider implements AuthenticationProvider{

    private ISysUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        //userService = SpringContextHolder.getBean(IUserService.class);

        String mobile = (String) smsCodeAuthenticationToken.getPrincipal();

        //校验手机号验证码
        checkSmsCode(mobile);

        SysUser sysUser = userService.getUserByMobile(mobile);

        if(null == sysUser){
            throw new BadCredentialsException("Invalid mobile!");
        }
        log.info("user info:{}", sysUser);

        //授权通过
        UserDetails userDetails = buildUserDetails(sysUser);
        return new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

    }

    /**
     * 构建用户认证信息
     * @param sysUser 用户对象
     * @return UserDetails
     */
    private UserDetails buildUserDetails(SysUser sysUser) {
        Set<String> authSet = new HashSet<>();
        List<String> roles = sysUser.getRoles();
        if(!CollectionUtils.isEmpty(roles)){
            roles.forEach(item -> authSet.add(CloudConstant.ROLE_PREFIX + item));
            authSet.addAll(sysUser.getPermissions());
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(authSet.toArray(new String[0]));

        return new SecurityUser(
                sysUser.getId(),
                sysUser.getMobile(),
                sysUser.getUsername(),
                sysUser.getPassword(),
                authorityList
        );
    }

    /**
     * 校验手机号与验证码的绑定关系是否正确
     *  todo 需要根据业务逻辑自行处理
     * @author javadaily
     * @date 2020/7/23 17:31
     * @param mobile 手机号码
     */
    private void checkSmsCode(String mobile) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取验证码
        String smsCode = request.getParameter("smsCode");
        if(StringUtils.isEmpty(smsCode) || !"666666".equals(smsCode)){
            throw new BadCredentialsException("Incorrect sms code,please check !");
        }
        //todo  手机号与验证码是否匹配
    }

    /**
     * ProviderManager 选择具体Provider时根据此方法判断
     * 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserService(ISysUserService userService) {
        this.userService = userService;
    }
}
