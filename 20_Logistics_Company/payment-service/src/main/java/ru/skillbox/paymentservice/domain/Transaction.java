package ru.skillbox.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    private Long orderId;
    private double price;

    public Transaction orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Transaction price(double price) {
        this.price = price;
        return this;
    }
}
