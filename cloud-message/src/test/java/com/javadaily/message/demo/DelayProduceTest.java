package com.javadaily.message.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DelayProduceTest {
    @Autowired
    private DelayProduce delayProduce;

    @Test
    public void sendDelayMessage() {
        //1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        delayProduce.sendDelayMessage("delay-topic","Hello,World!",1);
    }
}