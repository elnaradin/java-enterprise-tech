package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageResource {

    private final KafkaTemplate<Long, UserMessage> kafkaTemplate;

    @PostMapping("/send/{topic}")
    public String sendMessage (@PathVariable String topic, @RequestBody UserMessage userMessage){

        kafkaTemplate.send(topic,userMessage);
        return "message sent to topic " + topic;
    }
}
