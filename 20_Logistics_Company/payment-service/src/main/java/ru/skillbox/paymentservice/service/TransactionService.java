package ru.skillbox.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.skillbox.paymentservice.domain.Transaction;
import ru.skillbox.paymentservice.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Flux<Transaction> getAll() {
        return Flux.fromIterable(new ArrayList<>());
    }

    public Flux<List<Transaction>> reactiveGetAll() {
        return Flux.fromIterable(new ArrayList<>());
    }

    public Mono<Transaction> getOrderById(Integer id) {
        return Mono.fromCallable(Transaction::new);
    }

}
