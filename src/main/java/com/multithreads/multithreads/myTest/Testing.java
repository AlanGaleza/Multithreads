package com.multithreads.multithreads.myTest;

public class Testing {

    public static void main(String[] args) throws InterruptedException {
    Subtraction subtraction = new Subtraction();
    Addition addition = new Addition();

    subtraction.start();
    addition.start();

    subtraction.join();
    addition.join();

    System.out.println("This lane shall appear after subtraction and addition ends");
    }
}
