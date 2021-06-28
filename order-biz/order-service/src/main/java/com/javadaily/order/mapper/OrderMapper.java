package com.javadaily.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javadaily.order.po.Order;
import org.apache.ibatis.annotations.Param;

/**
 * Account Dao层
 * @author JAVA日知录
 */
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 根据订单编码获取订单
     */
    Order selectByNo(@Param("orderNo") String orderNo);

    /**
     * 修改订单状态
     */
    void changeStatus(@Param("id") Integer id, @Param("status") String status);
}
