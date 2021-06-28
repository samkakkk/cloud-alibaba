package com.javadaily.order.service;

import com.javadaily.order.dto.OrderDTO;

/**
 * <p>
 * <code>OrderService</code>
 * </p>
 * Description:
 * @author jianzh5
 * @date 2019/12/16 16:55
 */
public interface OrderService {
    void createOrder(OrderDTO orderDTO);

    /**
     * 根据订单编号查询订单
     */
    OrderDTO selectByNo(String orderNo);

    /**
     * 根据id改变状态
     */
    void changeStatus(Integer id, String status);

    /**
     * 根据订单编码删除订单
     */
    void delete(String orderNo);

    void changeStatuswithRocketMqLog(Integer id,String status,String transactionId);


}
