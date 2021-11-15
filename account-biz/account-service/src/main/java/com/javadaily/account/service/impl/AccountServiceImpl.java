package com.javadaily.account.service.impl;

import com.javadaily.account.mapper.AccountMapper;
import com.javadaily.account.po.Account;
import com.javadaily.account.service.AccountService;
import com.javadaily.account.dto.AccountDTO;
import com.javadaily.exception.BizException;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Description:
 * Account Service 接口层
 * @author JAVA日知录
 * @date 2019/12/2 15:09
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    @Override
    public AccountDTO selectByCode(String accountCode) {
        if("javadaily".equals(accountCode)){
            throw new BizException(accountCode + "用户不存在");
        }
        AccountDTO accountDTO = new AccountDTO();
        Account account = accountMapper.selectByCode(accountCode);
        BeanUtils.copyProperties(account,accountDTO);
        return accountDTO;
    }

    @Override
    public void updateAccount(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO,account);
        accountMapper.updateById(account);
    }

    @Override
    public void insertAccount(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO,account);
        accountMapper.insert(account);
    }

    @Override
    public void deleteAccount(String accountCode) {
        accountMapper.deleteByCode(accountCode);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void reduceAccount(String accountCode, BigDecimal amount) {
        log.info("Account XID is: {}", RootContext.getXID());
        Account account = accountMapper.selectByCode(accountCode);
        if(null == account){
            throw new RuntimeException("can't reduce amount,account is null");
        }
        BigDecimal subAmount = account.getAmount().subtract(amount);
        if(subAmount.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("can't reduce amount,account'amount is less than reduce amount");
        }
        account.setAmount(subAmount);
        accountMapper.updateById(account);
    }
}
