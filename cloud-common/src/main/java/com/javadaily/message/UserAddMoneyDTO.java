package com.javadaily.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * <p>
 * <code>MessageDTO</code>
 * </p>
 * Description:
 * 消息发送
 * @author jianzh5
 * @date 2020/3/31 17:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddMoneyDTO {
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 金额
     */
    private BigDecimal amount;
}
