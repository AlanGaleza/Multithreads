package com.multithreads.multithreads.MultipleLocks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Processor {
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

    public List<Integer> getList1() {
        return list1;
    }

    public List<Integer> getList2() {
        return list2;
    }
}
