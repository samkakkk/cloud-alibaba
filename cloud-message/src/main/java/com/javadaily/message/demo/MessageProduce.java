package com.javadaily.message.demo;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <code>MessageProduce</code>
 * </p>
 * Description:
 * 使用RocketMQTemplate消息发送
 * @author javadaily
 * @date 2020/3/29 17:50
 */
@Component
public class MessageProduce {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息
     * @param topic 主题
     * @param message 消息体
     */
    public void sendMessage(String topic,String message){
        this.rocketMQTemplate.convertAndSend(topic,message);
    }
}
