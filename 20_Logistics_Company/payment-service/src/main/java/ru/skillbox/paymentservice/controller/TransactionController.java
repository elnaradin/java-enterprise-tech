package ru.skillbox.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.skillbox.paymentservice.domain.Transaction;
import ru.skillbox.paymentservice.service.TransactionService;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("transactions/all")
    public Flux<Transaction> getAllOrders() {
        return transactionService.getAll();
    }

    @GetMapping(path = "transactions/all/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Transaction>> getAllOrdersStream() {
        return transactionService.reactiveGetAll();
    }

    @GetMapping("transactions/{id}")
    public Mono<Transaction> getOrderById(@PathVariable Integer id) {
        return transactionService.getOrderById(id);
    }

}
