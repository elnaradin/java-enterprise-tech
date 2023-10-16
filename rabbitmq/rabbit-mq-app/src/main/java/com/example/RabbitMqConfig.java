package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    @Bean
    public Queue firstQueue(){
        return new Queue("first.queue", false);
    }
    @Bean
    public Queue secondQueue(){
        return new Queue("second.queue", false);
    }
    @Bean
    public Queue thirdQueue(){
        return new Queue("third.queue", false);
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("my.direct.exchange");
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("my.topic.exchange");
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("my.fanout.exchange");
    }
    @Bean
    public Binding firstQueueToMyDirectExchangeBinding(){
        return BindingBuilder
                .bind(firstQueue())
                .to(directExchange())
                .with("first.queue");
    }

    @Bean
    public Binding secondQueueToMyDirectExchangeBinding(){
        return BindingBuilder
                .bind(secondQueue())
                .to(directExchange())
                .with("second.queue");
    }
    @Bean
    public Binding thirdQueueToMyToppicExchangeBinding(){
        return BindingBuilder
                .bind(thirdQueue())
                .to(topicExchange())
                .with("third.*.queue");
    }
    @Bean
    public Binding firstQueueToMyFanoutExchangeBinding(){
        return BindingBuilder
                .bind(firstQueue())
                .to(fanoutExchange());
    }
    @Bean
    public Binding thirdQueueToMyFanoutExchangeBinding(){
        return BindingBuilder
                .bind(thirdQueue())
                .to(fanoutExchange());
    }
}

