package com.javadaily.order.client.fallback;

import com.javadaily.account.dto.ProductDTO;
import com.javadaily.base.ResultData;
import com.javadaily.order.client.ProductClient;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * <code>ProductClientFallback</code>
 * </p>
 * Description:
 * 熔断
 * @author jianzh5
 * @date 2020/10/13 15:41
 */
@Slf4j
public class ProductClientFallback implements ProductClient {
    private Throwable throwable;

    public ProductClientFallback(Throwable throwable){
        this.throwable = throwable;
    }

    @Override
    public ResultData<String> insert(ProductDTO productDTO) {
        return ResultData.fail("接口熔断");
    }

    @Override
    public ResultData<String> delete(String accountCode) {
        return ResultData.fail("接口熔断");
    }

    @Override
    public ResultData<String> update(ProductDTO productDTO) {
        return ResultData.fail("接口熔断");
    }

    @Override
    public ResultData<ProductDTO> getByCode(String productCode) {
        log.error("查询失败,接口异常" ,throwable);
        ProductDTO product = new ProductDTO();
        product.setProductCode("000");
        product.setProductName("测试Feign");
        return ResultData.success(product);
    }


    @Override
    public ResultData<String> deduct(String productCode, Integer count) {
        return ResultData.fail("接口熔断");
    }

}
