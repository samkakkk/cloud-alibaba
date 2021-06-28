package com.javadaily.message.stream;

import org.springframework.stereotype.Service;

/**
 * <p>
 * <code>ProduceService</code>
 * </p>
 * Description:
 * 消息发送类
 * @author jianzh5
 * @date 2020/3/31 9:29
 */
@Service
public class ProduceService {
   /* @Autowired
    private Source source;

    public void sendMessage(String msg){
        source.output().send(MessageBuilder.withPayload(msg).build());
    }

    public <T> void sendMessage(T msg, String tag){
        Message message = MessageBuilder.createMessage(
                msg,new MessageHeaders(Stream.of(tag).collect(Collectors
                .toMap(str -> MessageConst.PROPERTY_TAGS, String::toString))
        ));

        source.output().send(message);
    }

    public <T> void sendObject(T msg) throws Exception {
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        source.output().send(message);
    }

    public <T> void sendObject(T msg, String tag) throws Exception {
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS, tag)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        source.output().send(message);
    }

    public <T> void sendTransactionalMsg(T msg, int num) throws Exception {
        MessageBuilder builder = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        builder.setHeader("test", String.valueOf(num));
        builder.setHeader(RocketMQHeaders.TAGS, "binder");
        Message message = builder.build();
        source.output().send(message);
    }*/

}
