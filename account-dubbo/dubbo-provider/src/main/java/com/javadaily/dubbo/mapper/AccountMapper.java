package com.javadaily.dubbo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javadaily.dubbo.po.Account;
import org.apache.ibatis.annotations.Param;

/**
 * Account Dao层
 * @author JAVA日知录
 */
public interface AccountMapper extends BaseMapper<Account> {

    Account selectByCode(@Param("accountCode") String accountCode);

}
