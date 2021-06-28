package com.javadaily.message.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <code>MessageConsumer</code>
 * </p>
 * Description:
 * 消息消费类
 * @author jianzh5
 * @date 2020/3/29 17:54
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "test-topic",
        consumerGroup = "test-group"
)
public class MessageConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("received message is {}", message);
    }
}
