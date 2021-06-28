package com.javadaily.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javadaily.auth.po.SysPermission;

import java.util.List;

/**
 * <p>
 * <code>ISysRoleService</code>
 * </p>
 * Description:
 * @author jianzh5
 * @date 2020/8/5 9:44
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 获取所有角色的权限
     * @author javadaily
     * @date 2020/8/5 12:46
     * @param roleIds 角色id列表
     * @return List<SysPermission>
     */
    List<SysPermission> listPermissionsByRoles(List<Integer> roleIds);
}
