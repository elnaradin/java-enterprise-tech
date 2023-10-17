package ru.skillbox.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.skillbox.paymentservice.domain.Transaction;
import ru.skillbox.paymentservice.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final Scheduler jdbcScheduler;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              Scheduler jdbcScheduler) {
        this.transactionRepository = transactionRepository;
        this.jdbcScheduler = jdbcScheduler;
    }

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
