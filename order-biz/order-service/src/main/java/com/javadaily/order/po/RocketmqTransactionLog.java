package com.javadaily.order.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("rocketmq_transaction_log")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RocketmqTransactionLog {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String transactionId;
    private String log;
}
