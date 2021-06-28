package com.javadaily.auth.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * <code>AppUser</code>
 * </p>
 * Description:
 * 用户
 * @author jianzh5
 * @date 2020/2/26 15:53
 */
@Data
@TableName("sys_user")
public class SysUser {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String mobile;

    @TableField(exist = false)
    private List<String> roles;

    @TableField(exist = false)
    private List<String> permissions;
}
