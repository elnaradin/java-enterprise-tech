package ru.skillbox.paymentservice.domain.dto;

import lombok.Data;
import ru.skillbox.paymentservice.domain.OrderStatus;

@Data
public class StatusDto {
    private OrderStatus status;
}
