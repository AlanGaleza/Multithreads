package com.multithreads.multithreads.InterruptingThreads;

import java.util.concurrent.ThreadLocalRandom;

public class InterruptingThreads {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1E8; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }
                    /*try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                        break;
                    }
                    */
                    Math.sin(ThreadLocalRandom.current().nextDouble());
                }
            }
        });

        t1.start();
        Thread.sleep(500);
        t1.interrupt();
        t1.join();

        System.out.println("Finished.");
    }
}
