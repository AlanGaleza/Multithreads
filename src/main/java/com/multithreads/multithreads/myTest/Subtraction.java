package com.multithreads.multithreads.myTest;

public class Subtraction extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("W8ting 3 sec. to subtract");
            Thread.sleep(3000);
            System.out.println("\n 3 sec passed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Subtraction has end");
    }
}
