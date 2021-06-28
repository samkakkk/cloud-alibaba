package com.javadaily.dubbo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 接口传输层
 * @author JAVA日知录
 * @date 2019/12/4 16:27
 */
@Data
public class AccountDTO implements Serializable {
    private Integer id;
    private String accountCode;
    private String accountName;
    private BigDecimal amount;
}
