package ru.skillbox.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skillbox.orderservice.controller.OrderNotFoundException;
import ru.skillbox.orderservice.domain.Order;
import ru.skillbox.orderservice.domain.OrderStatus;
import ru.skillbox.orderservice.domain.dto.OrderDto;
import ru.skillbox.orderservice.domain.dto.OrderKafkaDto;
import ru.skillbox.orderservice.domain.dto.StatusDto;
import ru.skillbox.orderservice.handler.OrderProcessor;
import ru.skillbox.orderservice.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final KafkaService kafkaService;

    private final OrderProcessor orderProcessor;

    @Override
    public Optional<Order> addOrder(OrderDto orderDto) {

        LocalDateTime dateTime = LocalDateTime.now();
        Order newOrder = new Order(
                orderDto.getDepartureAddress(),
                orderDto.getDestinationAddress(),
                orderDto.getDescription(),
                orderDto.getCost(),
                dateTime,
                dateTime,
                OrderStatus.CREATED
        );
        Order order = orderRepository.save(newOrder);
        orderProcessor.process(orderDto, order.getId());
        return Optional.of(order);
    }

    @Override
    public Boolean updateOrderStatus(Long id, StatusDto statusDto) {
        try {
            Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
            order.setStatus(statusDto.getStatus());
            Order resultOrder = orderRepository.save(order);
            kafkaService.produce(OrderKafkaDto.toKafkaDto(resultOrder));
            return true;
        } catch (RuntimeException e) {
            logger.error(String.format("Update status failed: %s", e.getMessage()));
            return false;
        }
    }

}
