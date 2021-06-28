package com.javadaily.product.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:
 *  产品类
 * @author jianzh5
 * @date 2019/12/2 15:06
 */
@Data
@TableName("product")
public class Product {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String productCode;
    private String productName;
    private Integer count;
    private BigDecimal price;
}
