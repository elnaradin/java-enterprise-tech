package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MessageResource {
    private final JmsTemplate jmsTemplate;
    private final JmsTemplate jmsTemplateTopic;

    public MessageResource(@Qualifier("jmsTemplate") JmsTemplate jmsTemplate,
                           @Qualifier("jmsTemplateTopic") JmsTemplate jmsTemplateTopic) {
        this.jmsTemplate = jmsTemplate;
        this.jmsTemplateTopic = jmsTemplateTopic;
    }

    @PostMapping("/send/queue")
    public String sendToQueue(@RequestBody UserMessage userMessage) {
        userMessage.setTimestamp(LocalDateTime.now());
        jmsTemplate.convertAndSend("queue", userMessage);
        return "message sent to queue";
    }
    @PostMapping("/send/topic")
    public String sendToTopic(@RequestBody UserMessage userMessage) {
        userMessage.setTimestamp(LocalDateTime.now());
        jmsTemplateTopic.convertAndSend("topic", userMessage);
        return "message sent to topic";
    }

}
