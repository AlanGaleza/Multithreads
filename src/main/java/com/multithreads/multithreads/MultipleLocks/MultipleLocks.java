package com.multithreads.multithreads.MultipleLocks;

public class MultipleLocks {

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        System.out.println("Starting...");

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.process();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.process();
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
        System.out.println("List1: " + processor.getList1().size() + "; List2: " + processor.getList2().size());
    }
}
