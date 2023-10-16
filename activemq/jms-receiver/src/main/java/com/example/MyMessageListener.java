package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(MyMessageListener.class);
    @JmsListener(destination = "queue", containerFactory = "jmsListenerContainerFactory")
    public void onMessageInQueue(UserMessage userMessage){
        logger.info("new message from queue {}", userMessage);
    }
    @JmsListener(destination = "topic", containerFactory = "jmsTopicListenerContainerFactory")
    public void onMessageInTopic(UserMessage userMessage){
        logger.info("new message from topic {}", userMessage);
    }
}
