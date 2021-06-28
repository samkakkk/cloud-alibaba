package com.javadaily.message.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <code>ReceiveService</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/3/31 9:27
 */
@Service
@Slf4j
public class ReceiveService {

    /**
     * receive message
     * @param message
     */
//    @StreamListener(Sink.INPUT)
//    public void receiveInput(String message){
//      log.info("stream received message is {}", message);
//    }

    /**
     * receive object
     * @param foo
     */
   /* @StreamListener("input")
    public void receiveInput(@Payload Foo foo){
        log.info("stream received object is {}", foo);
    }*/

}
