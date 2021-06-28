package com.javadaily.order.client.fallback;

import com.javadaily.base.ResultData;
import com.javadaily.order.client.AccountClient;
import com.javadaily.account.dto.AccountDTO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * <p>
 * <code>AccountFeignFallback</code>
 * </p>
 * Description:
 * 熔断
 * @author jianzh5
 * @date 2020/10/13 15:41
 */
@Slf4j
public class AccountClientFallback implements AccountClient {
    @Setter
    private Throwable cause;


    @Override
    public ResultData<String> insert(AccountDTO accountDTO) {
        return ResultData.fail("接口熔断");
    }

    @Override
    public ResultData<String> delete(String accountCode) {
        return ResultData.fail("接口熔断");
    }

    @Override
    public ResultData<String> update(AccountDTO accountDTO) {
        return ResultData.fail("接口熔断");
    }

    @Override
    public ResultData<AccountDTO> getByCode(String accountCode) {
        log.error("查询失败,接口异常" ,cause);
        AccountDTO account = new AccountDTO();
        account.setAccountCode("000");
        account.setAccountName("测试Feign");
        return ResultData.success(account);
    }

    @Override
    public ResultData<String> reduce(String accountCode, BigDecimal amount) {
        return ResultData.fail("接口熔断");
    }
}
