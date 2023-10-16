package com.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public MessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    @Bean
    public Queue pictureQueue(){
        return new Queue("picture.queue", false);
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("picture.direct.exchange");
    }
    @Bean
    public Binding pictureQueueToPictureDurectExchangeBinding(){
        return BindingBuilder
                .bind(pictureQueue())
                .to(directExchange())
                .with("picture.key");
    }
}
