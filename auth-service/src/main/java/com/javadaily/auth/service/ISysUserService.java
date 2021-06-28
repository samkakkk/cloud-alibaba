package com.javadaily.auth.service;

import com.javadaily.auth.po.SysUser;

/**
 * <p>
 * <code>UserService</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/7/23 16:20
 */
public interface ISysUserService {
    /**
     * 根据手机号码获取用户信息
     * @author javadaily
     * @date 2020/7/23 16:23
     * @param mobile 手机号码
     * @return 用户信息
     */
    SysUser getUserByMobile(String mobile);
}
