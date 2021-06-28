package com.javadaily.account.api;

import com.javadaily.account.dto.ProductDTO;
import com.javadaily.base.ResultData;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * <code>ProductFeign</code>
 * </p>
 * Description:
 * @author JAVA日知录
 */
//@FeignClient(value = "product-service")
public interface ProductApi {
    /** 新增产品 */
    @PostMapping("/product/insert")
    ResultData<String> insert(@RequestBody ProductDTO productDTO);

    /** 删除产品 */
    @PostMapping("/product/delete")
    ResultData<String> delete(@RequestParam("productCode") String productCode);

    /** 编辑产品 */
    @PostMapping("/product/update")
    ResultData<String> update(@RequestBody ProductDTO productDTO);

    /** 查找产品 */
    @GetMapping("/product/getByCode/{productCode}")
    ResultData<ProductDTO> getByCode(@PathVariable(value = "productCode") String productCode);

    /**扣减库存*/
    @PostMapping("/product/deduct")
    ResultData<String> deduct(@RequestParam("productCode")String productCode, @RequestParam("count")Integer count);
}
