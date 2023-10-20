package ru.skillbox.paymentservice.config;

import com.example.logysticssystemcommon.event.OrderCreatedEvent;
import com.example.logysticssystemcommon.event.PaymentEvent;
import com.example.logysticssystemcommon.event.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skillbox.paymentservice.handler.EventHandler;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class PaymentServiceConfig {

    private final EventHandler<PaymentEvent, TransactionEvent> paymentEventHandler;
    private final EventHandler<OrderCreatedEvent, PaymentEvent> orderCreatedEventHandler;

    @Bean
    public Function<OrderCreatedEvent, PaymentEvent> orderEventProcessor() {
        return orderCreatedEventHandler::handleEvent;
    }

    @Bean
    public Function<PaymentEvent, TransactionEvent> paymentEventSubscriber() {
        return paymentEventHandler::handleEvent;
    }

}
