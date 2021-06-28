package com.javadaily.account.service;

import com.javadaily.account.dto.AccountDTO;

import java.math.BigDecimal;

/**
 * Account Service 接口层
 * @author JAVA日知录
 * @date 2019/12/2 15:09
 */
public interface AccountService {

    /**新增用户*/
    void insertAccount(AccountDTO accountDTO);

    /**删除用户*/
    void deleteAccount(String accountCode);

    /**更新用户*/
    void updateAccount(AccountDTO accountDTO);

    /**根据用户编码查找用户详细信息*/
    AccountDTO selectByCode(String accountCode);

    /**扣减账户余额*/
    void reduceAccount(String accountCode, BigDecimal amount);
}
