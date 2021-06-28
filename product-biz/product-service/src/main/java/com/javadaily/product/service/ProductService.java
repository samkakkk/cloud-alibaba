package com.javadaily.product.service;


import com.javadaily.account.dto.ProductDTO;

/**
 * Product Service 接口层
 * @author JAVA日知录
 * @date 2019/12/2 15:09
 */
public interface ProductService {
    /**
     * 新增产品
     */
    ProductDTO insertProduct(ProductDTO accountVO);

    /**
     * 删除产品
     */
    int deleteProduct(String accountCode);

    /**
     * 更新产品
     */
    ProductDTO updateProduct(ProductDTO accountVO);

    /**
     * 根据产品编码查找产品详细信息
     */
    ProductDTO selectByCode(String productCode);

    /**
     * 扣减库存
     */
    void deduct(String productCode, Integer count);
}
