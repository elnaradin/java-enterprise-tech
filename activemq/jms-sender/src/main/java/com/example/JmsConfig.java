package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
@Configuration
public class JmsConfig {
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    @Bean
    public JmsTemplate jmsTemplateTopic(
            ConnectionFactory connectionFactory,
            MessageConverter jacksonJmsMessageConverter){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory );
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory,
                                   MessageConverter jacksonJmsMessageConverter){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory );
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
        return jmsTemplate;
    }
}
