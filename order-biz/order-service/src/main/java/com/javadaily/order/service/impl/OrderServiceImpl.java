package com.javadaily.order.service.impl;

import com.javadaily.base.CloudConstant;
import com.javadaily.account.api.AccountApi;
import com.javadaily.account.api.ProductApi;
import com.javadaily.message.UserAddMoneyDTO;
import com.javadaily.order.dto.OrderDTO;
import com.javadaily.order.mapper.OrderMapper;
import com.javadaily.order.mapper.RocketMqTransactionLogMapper;
import com.javadaily.order.po.Order;
import com.javadaily.order.po.RocketmqTransactionLog;
import com.javadaily.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * <p>
 * <code>OrderServiceImpl</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2019/12/16 16:56
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {
    private final AccountApi accountApi;
    private final ProductApi productApi;
    private final OrderMapper orderMapper;
    private final RocketMQTemplate rocketMQTemplate;
    private final RocketMqTransactionLogMapper rocketMqTransactionLogMapper;

    @GlobalTransactional(name = "TX_ORDER_CREATE")
    @Override
    public void createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        //本地存储Order
        this.saveOrder(order);
        log.info("ORDER XID is: {}", RootContext.getXID());
        //账户余额扣减
        accountApi.reduce(orderDTO.getAccountCode(), orderDTO.getAmount());
        //库存扣减
        productApi.deduct(orderDTO.getProductCode(),orderDTO.getCount());
    }


    @Transactional(rollbackFor = RuntimeException.class)
    void saveOrder(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public OrderDTO selectByNo(String orderNo) {
        OrderDTO orderDTO = new OrderDTO();
        Order order = orderMapper.selectByNo(orderNo);
        BeanUtils.copyProperties(order,orderDTO);
        return orderDTO;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void changeStatus(Integer id, String status) {
        orderMapper.changeStatus(id,status);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void changeStatuswithRocketMqLog(Integer id,String status,String transactionId){
        orderMapper.changeStatus(id,status);
        rocketMqTransactionLogMapper.insert(
                RocketmqTransactionLog.builder()
                        .transactionId(transactionId)
                        .log("执行删除订单操作")
                .build()
        );
    }

    @Override
    public void delete(String orderNo) {
        Order order = orderMapper.selectByNo(orderNo);
        //如果订单存在且状态为有效，进行业务处理
        if (order != null && CloudConstant.VALID_STATUS.equals(order.getStatus())) {
            String transactionId = UUID.randomUUID().toString();
            //如果可以删除订单则发送消息给rocketmq，让用户中心消费消息

            rocketMQTemplate.sendMessageInTransaction("add-amount",
                    MessageBuilder.withPayload(
                            UserAddMoneyDTO.builder()
                                    .userCode(order.getAccountCode())
                                    .amount(order.getAmount())
                                    .build()
                    )
                    .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                    .setHeader("order_id",order.getId())
                    .build()
                    ,order
            );

//            changeStatus(order.getId(), CloudConstant.INVALID_STATUS);
        }
        //throw new RuntimeException("订单状态");
    }
}
