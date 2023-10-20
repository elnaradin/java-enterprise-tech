package com.example.logysticssystemcommon.event;

public class PaymentEvent implements Event {

    private static final String EVENT = "Payment";

    private Long orderId;
    private PaymentStatus status;
    private double price;

    public PaymentEvent() {
    }

    public PaymentEvent orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public PaymentEvent status(PaymentStatus status) {
        this.status = status;
        return this;
    }

    public PaymentEvent price(double price) {
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

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
