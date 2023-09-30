package org.example;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Service {
    private Integer count = 0;
    private final LinkedList<String> log = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    public void log(){
        lock.lock();
        count += 1;
        log.add(Thread.currentThread().getName());
        lock.unlock();
    }
    public void debug(){
        System.out.println(count);
        log.forEach(System.out::println);
    }
}
