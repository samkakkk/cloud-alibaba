package com.javadaily.dubbo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:
 *  用户类
 * @author JAVA日知录
 * @date 2019/12/2 15:06
 */
@Data
@TableName("account")
public class Account {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String accountCode;
    private String accountName;
    private BigDecimal amount;
}
