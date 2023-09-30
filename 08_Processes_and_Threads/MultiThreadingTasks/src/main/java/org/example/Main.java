package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var counter = new Service();
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Thread thread = new Thread(() -> IntStream.range(0, 10).forEach((j) -> counter.log()), "T" + i);
            threads.add(thread);
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        counter.debug();
    }
}