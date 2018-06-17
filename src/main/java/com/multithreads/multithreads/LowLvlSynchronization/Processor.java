package com.multithreads.multithreads.LowLvlSynchronization;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Processor {
    private final int LIMIT = 10;
    private List<Integer> list = new LinkedList<>();
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;

        while(true) {
            synchronized (lock) {
                while(list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        while(true) {
            synchronized (lock) {
                while(list.size() == 0) {
                    lock.wait();
                }
                System.out.println("List size is: " + list.size());
                int value = list.remove(0);
                System.out.println("; value is: " + value);

                lock.notify();
            }
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        }
    }
}
