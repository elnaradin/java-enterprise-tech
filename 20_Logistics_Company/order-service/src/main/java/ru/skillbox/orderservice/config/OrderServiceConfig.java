package ru.skillbox.orderservice.config;

import com.example.logysticssystemcommon.event.OrderCreatedEvent;
import com.example.logysticssystemcommon.event.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import ru.skillbox.orderservice.handler.EventConsumer;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
@RequiredArgsConstructor
public class OrderServiceConfig {
    private final EventConsumer<TransactionEvent> transactionEventHandler;

    @Bean
    public Consumer<TransactionEvent> transactionEventConsumer() {
        return transactionEventHandler::consumeEvent;
    }

    @Bean
    public Sinks.Many<OrderCreatedEvent> sink() {
        return Sinks.many()
                .multicast()
                .directBestEffort();
    }

    @Bean
    public Supplier<Flux<OrderCreatedEvent>> orderCreatedEventPublisher(
            Sinks.Many<OrderCreatedEvent> publisher
    ) {
        return publisher::asFlux;
    }
}
