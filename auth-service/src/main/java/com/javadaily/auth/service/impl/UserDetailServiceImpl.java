package com.javadaily.auth.service.impl;

import com.javadaily.auth.mapper.SysUserMapper;
import com.javadaily.auth.po.SysPermission;
import com.javadaily.auth.po.SysRole;
import com.javadaily.auth.po.SysUser;
import com.javadaily.auth.service.ISysPermissionService;
import com.javadaily.auth.service.ISysRoleService;
import com.javadaily.base.CloudConstant;
import com.javadaily.component.security.user.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * <code>MyUserDetailService</code>
 * </p>
 * Description:
 * 自定义用户实现
 * @author jianzh5
 * @date 2020/2/26 16:18
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取本地用户
        SysUser sysUser = sysUserMapper.selectByUserName(userName);
        if(sysUser != null){
            //获取当前用户的所有角色
            List<SysRole> roleList = sysRoleService.listRolesByUserId(sysUser.getId());
            sysUser.setRoles(roleList.stream().map(SysRole::getRoleCode).collect(Collectors.toList()));
            List<Integer> roleIds = roleList.stream().map(SysRole::getId).collect(Collectors.toList());
            //获取所有角色的权限
            List<SysPermission> permissionList = sysPermissionService.listPermissionsByRoles(roleIds);

            //基于路径拦截并处理restful接口
//            List<String> permissionUrlList = permissionList.stream()
//                    .map(item -> "["+item.getMethod()+"]"+item.getUrl())
//                    .collect(Collectors.toList());
//            sysUser.setPermissions(permissionUrlList);

            //基于方法拦截.只需放入用户权限标识即可
            List<String> permissionMethodList = permissionList.stream()
                    .map(SysPermission::getPermission)
                    .collect(Collectors.toList());
            sysUser.setPermissions(permissionMethodList);
            //构建oauth2的用户
            return buildUserDetails(sysUser);

        }else{
            throw  new UsernameNotFoundException("用户["+userName+"]不存在");
        }
    }

    /**
     * 构建oAuth2用户，将角色和权限赋值给用户，角色使用ROLE_作为前缀
     * @author javadaily
     * @date 2020/8/5 14:50
     * @param sysUser 系统用户
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
}
