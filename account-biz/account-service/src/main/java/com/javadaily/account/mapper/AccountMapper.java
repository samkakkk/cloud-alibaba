package com.javadaily.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javadaily.account.po.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * Account Dao层
 * @author JAVA日知录
 */
public interface AccountMapper extends BaseMapper<Account> {

    Account selectByCode(@Param("accountCode") String accountCode);

    int deleteByCode(@Param("accountCode") String accountCode);

    void increaseAmount(@Param("accountCode") String accountCode, @Param("amount")BigDecimal amount);
}
