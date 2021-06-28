package com.javadaily.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javadaily.product.po.Product;
import org.apache.ibatis.annotations.Param;

/**
 * Product Dao层
 * @author JAVA日知录
 */
public interface ProductMapper extends BaseMapper<Product> {

    Product selectByCode(@Param("productCode") String productCode);

    int deleteByCode(@Param("productCode") String productCode);
}
