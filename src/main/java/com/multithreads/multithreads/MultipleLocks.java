package com.multithreads.multithreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MultipleLocks {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(1);

            list1.add(ThreadLocalRandom.current().nextInt(100));
        }
    }

    public void stageTwo() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(1);

            list2.add(ThreadLocalRandom.current().nextInt(100));
        }
    }

    public void process() throws InterruptedException {
        for(int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main1() throws InterruptedException {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t2.join();
        t1.join();

        long end = System.currentTimeMillis();

        System.out.println("Time takes: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }

    public static void main(String[] args) throws InterruptedException {
        MultipleLocks multipleLocks = new MultipleLocks();
        multipleLocks.main1();

    }
}
