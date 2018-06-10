package com.multithreads.multithreads;

import java.util.Scanner;

class Processor2 extends Thread {

    private volatile boolean running = true;
    @Override
    public void run() {
        while(running) {
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class BasicThreadSynchronization {
    public static void main(String[] args) {

        Processor2 processor2 = new Processor2();
        processor2.start();

        System.out.println("Press return to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        processor2.shutdown();

    }
}
