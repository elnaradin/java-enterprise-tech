package ru.skillbox.paymentservice.handler;


import com.example.logysticssystemcommon.event.Event;

public interface EventHandler<T extends Event, R extends Event> {

    R handleEvent(T event);
}
