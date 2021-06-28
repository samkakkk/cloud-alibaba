package com.javadaily.account.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description:
 *  用户类
 * @author JAVA日知录
 * @date 2021/1/12 15:06
 */
@Data
@TableName("blog_user")
public class BlogUser {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String nick;
    private String name;
}
