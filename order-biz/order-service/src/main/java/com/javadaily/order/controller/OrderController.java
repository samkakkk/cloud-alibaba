package com.javadaily.order.controller;

import com.javadaily.base.ResultData;
import com.javadaily.order.client.AccountClient;
import com.javadaily.order.dto.OrderDTO;
import com.javadaily.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author JAVA日知录
 * @date 2019/12/3 18:53
 */
@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired) )
public class OrderController {
    private final OrderService orderService;
    private final AccountClient accountClient;


    /**
     * 获取Order详情
     * @param orderNo order编号
     * @return ResultData<OrderDTO>
     */
    @GetMapping("/order/{orderNo}")
    public OrderDTO getById(@PathVariable("orderNo") String orderNo){
        OrderDTO orderDTO = orderService.selectByNo(orderNo);
        return orderDTO;
    }




    /**
     * 验证调用隐私接口
     * @return ResultData<String>
     */
    @GetMapping("/order/secret")
    public ResultData<String> secret(){
        ResultData<String> secretValue = accountClient.getSecretValue();
        log.info(secretValue);
        return secretValue;
    }



    @PostMapping("/order/create")
    public ResultData<OrderDTO> create(@RequestBody OrderDTO orderDTO){
        log.info("create order:{}",orderDTO);
        orderDTO.setOrderNo(UUID.randomUUID().toString());
        orderDTO.setAmount(orderDTO.getPrice().multiply(new BigDecimal(orderDTO.getCount())));
        orderService.createOrder(orderDTO);
        return ResultData.success("create order success");
    }


    /**
     * 根据订单号删除订单
     * @param orderNo 订单编号
     */
    @PostMapping("/order/delete")
    public ResultData<String> delete(@RequestParam String orderNo){
        log.info("delete order id is {}",orderNo);
        orderService.delete(orderNo);
        return ResultData.success("订单删除成功");
    }


}
