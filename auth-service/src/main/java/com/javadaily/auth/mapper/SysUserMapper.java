package com.javadaily.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javadaily.auth.po.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Product Dao层
 * @author JAVA日知录
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectByUserName(@Param("userName") String userName);

    @Select("select *  from sys_user WHERE MOBILE = #{mobile}")
    SysUser selectByMobile(@Param("mobile") String mobile);

}
