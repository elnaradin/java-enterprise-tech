package ru.skillbox.orderservice.handler;

import com.example.logysticssystemcommon.event.TransactionEvent;
import com.example.logysticssystemcommon.event.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skillbox.orderservice.domain.OrderStatus;
import ru.skillbox.orderservice.repository.OrderRepository;

@Component
@RequiredArgsConstructor
public class TransactionEventHandler implements EventConsumer<TransactionEvent> {
    private final OrderRepository orderRepository;

    @Override
    public void consumeEvent(TransactionEvent event) {
        orderRepository.findById(event.getOrderId())
                .ifPresent(order -> {
                    order.setStatus(event.getStatus() == TransactionStatus.SUCCESSFUL
                            ? OrderStatus.COMPLETED
                            : OrderStatus.FAILED);
                    orderRepository.save(order);
                });
    }
}
