package com.example;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class UserMessage {
    private String message;
    private String username;
    private LocalDateTime timestamp;

    public UserMessage(String message, String username) {
        this.message = message;
        this.username = username;
    }
}
