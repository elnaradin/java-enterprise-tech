package org.example;

import java.util.LinkedList;

public class Service {
    private Integer count = 0;
    private final LinkedList<String> log = new LinkedList<>();
    public synchronized void log(){
        count = count + 1;
        String name = Thread.currentThread().getName();
        log.add(name);
    }
    public void debug(){
        System.out.println(count);
        log.forEach(System.out::println);
    }
}
