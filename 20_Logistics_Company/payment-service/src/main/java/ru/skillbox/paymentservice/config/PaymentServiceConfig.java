package ru.skillbox.paymentservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skillbox.paymentservice.domain.OrderPurchaseEvent;
import ru.skillbox.paymentservice.domain.PaymentEvent;
import ru.skillbox.paymentservice.domain.TransactionEvent;
import ru.skillbox.paymentservice.handler.EventHandler;

import java.util.function.Function;

@Configuration
public class PaymentServiceConfig {

    private final EventHandler<PaymentEvent, TransactionEvent> paymentEventHandler;
    private final EventHandler<OrderPurchaseEvent, PaymentEvent> orderPurchaseEventHandler;

    @Autowired
    public PaymentServiceConfig(
            EventHandler<PaymentEvent, TransactionEvent> paymentEventHandler,
            EventHandler<OrderPurchaseEvent, PaymentEvent> orderPurchaseEventHandler) {
        this.paymentEventHandler = paymentEventHandler;
        this.orderPurchaseEventHandler = orderPurchaseEventHandler;
    }

    @Bean
    public Function<OrderPurchaseEvent, PaymentEvent> orderPurchaseEventProcessor() {
        return orderPurchaseEventHandler::handleEvent;
    }

    @Bean
    public Function<PaymentEvent, TransactionEvent> paymentEventSubscriber() {
        return paymentEventHandler::handleEvent;
    }

}
