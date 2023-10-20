package ru.skillbox.orderservice.handler;


import com.example.logysticssystemcommon.event.Event;

public interface EventConsumer<T extends Event> {
    void consumeEvent(T event);
}