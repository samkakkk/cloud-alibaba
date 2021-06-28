package com.javadaily.message.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * <code>DelayProduce</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/5/6 15:31
 */
@Component
@Slf4j
public class DelayProduce {
    @Autowired
    private RocketMQTemplate rocketMQTemplatet;

    //1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
    public void sendDelayMessage(String topic,String message,int delayLevel){
        SendResult sendResult = rocketMQTemplatet.syncSend(topic, MessageBuilder.withPayload(message).build(), 2000, delayLevel);
        log.info("sendtime is {}", DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss").format(LocalDateTime.now()));
        log.info("sendResult is{}",sendResult);
    }
}
