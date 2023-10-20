package ru.skillbox.orderservice.handler;

import com.example.logysticssystemcommon.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;
import ru.skillbox.orderservice.domain.dto.OrderDto;


@Component
@RequiredArgsConstructor
public class OrderProcessor {

    private final Sinks.Many<OrderCreatedEvent> sink;

    public void process(OrderDto orderDto, Long orderId) {
        OrderCreatedEvent event = new OrderCreatedEvent()
                .orderId(orderId)
                .userId(orderDto.getUserId())
                .price(orderDto.getCost());
        sink.emitNext(event, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
