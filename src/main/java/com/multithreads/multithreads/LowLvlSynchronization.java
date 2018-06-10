package com.multithreads.multithreads;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Processor4 {

    private List<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
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

public class LowLvlSynchronization {
    public static void main(String[] args) {

        final Processor4 processor4 = new Processor4();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor4.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor4.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
