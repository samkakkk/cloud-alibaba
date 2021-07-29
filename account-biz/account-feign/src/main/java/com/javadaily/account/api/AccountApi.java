package com.javadaily.account.api;

import com.javadaily.account.dto.AccountDTO;
import com.javadaily.base.ResultData;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 * <code>AccountFeign</code>
 * </p>
 * Description:
 * @author JAVA日知录
 */
//@FeignClient(name = "account-service",fallbackFactory = AccountFeignFallbackFactory.class)
public interface AccountApi {

    @PostMapping("/account/insert")
    ResultData<String> insert(@RequestBody AccountDTO accountDTO);

    @PostMapping("/account/delete")
    ResultData<String> delete(@RequestParam("accountCode") String accountCode);

    @PostMapping("/account/update")
    ResultData<String> update(@RequestBody AccountDTO accountDTO);

    @GetMapping("/account/getByCode/{accountCode}")
    ResultData<AccountDTO> getByCode(@PathVariable(value = "accountCode") String accountCode);

    @PostMapping("/account/reduce")
    ResultData<String> reduce(@RequestParam("accountCode")String accountCode, @RequestParam("amount") BigDecimal amount);

    @GetMapping("/account/getSecretValue")
    ResultData<String> getSecretValue();

}
