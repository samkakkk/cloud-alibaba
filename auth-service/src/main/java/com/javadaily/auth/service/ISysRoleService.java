package com.javadaily.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javadaily.auth.po.SysRole;

import java.util.List;

/**
 * <p>
 * <code>ISysRoleService</code>
 * </p>
 * Description:
 * @author jianzh5
 * @date 2020/8/5 9:44
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 根据用户id获取所有角色列表
     * @param userId 用户id
     * @return roleList
     */
    List<SysRole> listRolesByUserId(Integer userId);
}
