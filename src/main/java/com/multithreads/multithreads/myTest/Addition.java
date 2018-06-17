package com.multithreads.multithreads.myTest;

public class Addition extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("W8ting 1 sec. to add");
            Thread.sleep(1000);
            System.out.println("\n1 sec passed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Addition has end");
    }
}
