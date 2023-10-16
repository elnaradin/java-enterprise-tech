package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MessageResource {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send/{exchange}/{key}")
    public String sendMessage(@PathVariable String exchange,
                              @PathVariable String key,
                              @RequestBody UserMessage userMessage){
        userMessage.setTimestamp(LocalDateTime.now());
        rabbitTemplate.convertAndSend(exchange, key, userMessage);
        return "Message sent to " + exchange + " with routing key " + key;
    }
    @PostMapping("/send/{exchange}")
    public String sendMessageToAllQueues(@PathVariable String exchange,
                              @RequestBody UserMessage userMessage){
        userMessage.setTimestamp(LocalDateTime.now());
        rabbitTemplate.convertAndSend(exchange,"", userMessage);
        return "Message sent to " + exchange ;
    }
}
