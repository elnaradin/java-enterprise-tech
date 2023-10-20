package com.example.logysticssystemcommon.event;

public class OrderCreatedEvent implements Event {
    private static final String EVENT = "OrderPurchase";

    private Long orderId;
    private Long userId;
    private double price;

    public OrderCreatedEvent orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderCreatedEvent userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public OrderCreatedEvent price(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String getEvent() {
        return EVENT;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
