package com.javadaily.message.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageProduceTest {
    @Autowired
    private MessageProduce messageProduce;

    @Test
    public void testSendMessage() {
        for (int i = 0;i < 100;i++){
            messageProduce.sendMessage("test-topic","Hello,JAVA日知录:" + i);
        }
    }
}