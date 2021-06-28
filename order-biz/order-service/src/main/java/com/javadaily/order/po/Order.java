package com.javadaily.order.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_order")
public class Order {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String orderNo;
    private String accountCode;
    private String productCode;
    private Integer count;
    private BigDecimal amount;
    private String status;
}
