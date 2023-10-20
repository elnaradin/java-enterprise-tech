package ru.skillbox.paymentservice.handler;

import com.example.logysticssystemcommon.event.OrderCreatedEvent;
import com.example.logysticssystemcommon.event.PaymentEvent;
import com.example.logysticssystemcommon.event.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.skillbox.paymentservice.repository.UserRepository;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventHandler implements EventHandler<OrderCreatedEvent, PaymentEvent> {
    private static final Logger logger = LoggerFactory.getLogger(OrderCreatedEventHandler.class);

    private final UserRepository userRepository;

    @Override
    @Transactional
    public PaymentEvent handleEvent(OrderCreatedEvent event) {
        PaymentEvent paymentEvent = new PaymentEvent()
                .price(event.getPrice())
                .orderId(event.getOrderId());
        userRepository.findById(event.getUserId()).ifPresentOrElse(
                user -> {
                    double balance = user.getBalance();
                    if (balance >= event.getPrice()) {
                        user.setBalance(balance - event.getPrice());
                        userRepository.save(user);
                        paymentEvent.status(PaymentStatus.APPROVED);
                    } else {
                        paymentEvent.status(PaymentStatus.DECLINED);
                        logger.warn("Payment declined due to insufficient funds");
                    }
                },
                () -> {
                    paymentEvent.status(PaymentStatus.DECLINED);
                    logger.error("User not found");
                }
        );
        return paymentEvent;
    }
}
