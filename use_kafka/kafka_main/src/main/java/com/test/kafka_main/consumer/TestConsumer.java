package com.test.kafka_main.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    @KafkaListener(topics = "topic",groupId = "test_1")
    public void listen(Object message){
        System.out.println(message);
    }
}
