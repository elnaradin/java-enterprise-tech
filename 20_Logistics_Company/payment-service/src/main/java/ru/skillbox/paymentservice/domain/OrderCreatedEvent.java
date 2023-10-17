package ru.skillbox.paymentservice.domain;

import lombok.Data;

@Data
public class OrderCreatedEvent implements Event {

    private static final String EVENT = "OrderPurchase";

    private Integer orderId;
    private Integer userId;
    private double price;

    public Event setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Event setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Event setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String getEvent() {
        return EVENT;
    }
}
