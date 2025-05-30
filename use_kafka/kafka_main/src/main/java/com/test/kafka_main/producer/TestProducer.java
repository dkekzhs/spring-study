package com.test.kafka_main.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestProducer {
    private final KafkaTemplate<String,Object> kafkaTemplate ;

    public TestProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createMessage(){
        System.out.println("send topic message");
        kafkaTemplate.send("topic","123123123123");
    }
}
