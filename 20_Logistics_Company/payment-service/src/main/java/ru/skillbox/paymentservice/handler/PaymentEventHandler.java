package ru.skillbox.paymentservice.handler;

import com.example.logysticssystemcommon.event.PaymentEvent;
import com.example.logysticssystemcommon.event.PaymentStatus;
import com.example.logysticssystemcommon.event.TransactionEvent;
import com.example.logysticssystemcommon.event.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skillbox.paymentservice.domain.Transaction;
import ru.skillbox.paymentservice.repository.TransactionRepository;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class PaymentEventHandler implements EventHandler<PaymentEvent, TransactionEvent> {
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionEvent handleEvent(PaymentEvent event) {
        Transaction transaction = new Transaction()
                .orderId(event.getOrderId())
                .price(event.getPrice());
        transactionRepository.save(transaction);
        return new TransactionEvent()
                .orderId(event.getOrderId())
                .status(event.getStatus() == PaymentStatus.APPROVED
                        ? TransactionStatus.SUCCESSFUL
                        : TransactionStatus.UNSUCCESSFUL);
    }
}
