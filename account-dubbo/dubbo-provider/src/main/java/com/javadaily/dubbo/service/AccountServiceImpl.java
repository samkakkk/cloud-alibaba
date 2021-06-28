package com.javadaily.dubbo.service;

import com.javadaily.dubbo.api.AccountService;
import com.javadaily.dubbo.dto.AccountDTO;
import com.javadaily.dubbo.mapper.AccountMapper;
import com.javadaily.dubbo.po.Account;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * <code>AccountService</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/12/7 9:48
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountDTO getByCode(String accountCode) {
        AccountDTO accountDTO = new AccountDTO();
        Account account = accountMapper.selectByCode(accountCode);
        BeanUtils.copyProperties(account,accountDTO);
        return accountDTO;
    }
}
