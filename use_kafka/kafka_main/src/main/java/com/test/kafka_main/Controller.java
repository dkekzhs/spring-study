package com.test.kafka_main;

import com.test.kafka_main.consumer.TestConsumer;
import com.test.kafka_main.producer.TestProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final TestProducer testProducer;
    private final TestConsumer testConsumer;

    public Controller(TestProducer testProducer, TestConsumer testConsumer) {
        this.testProducer = testProducer;
        this.testConsumer = testConsumer;
    }


    @GetMapping("/")
    public String createTopicTest(){
        testProducer.createMessage();
        return "success";
    }

}
