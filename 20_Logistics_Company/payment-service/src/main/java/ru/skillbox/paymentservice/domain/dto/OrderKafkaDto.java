package ru.skillbox.paymentservice.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderKafkaDto {
    private Long id;
    private String status;
    private String creationTime;
    private String modifiedTime;

}
