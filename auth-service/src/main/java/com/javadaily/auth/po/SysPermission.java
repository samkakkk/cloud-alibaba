package com.javadaily.auth.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * <code>SysPermission</code>
 * </p>
 * Description:
 * 权限
 * @author jianzh5
 * @date 2020/2/26 15:53
 */
@Data
@TableName("sys_permission")
public class SysPermission {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String permission;
    private String url;
    private String method;
}
