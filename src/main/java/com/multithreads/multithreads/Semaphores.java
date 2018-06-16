package com.multithreads.multithreads;

import java.util.concurrent.*;

class Connection {
    private static Connection instance = new Connection();

    private Semaphore sem = new Semaphore(10);

    private int connections = 0;

    private Connection() {}

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            doConnect();
        }
        finally {
            sem.release();
        }
    }

    public void doConnect() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }
    }
}

public class Semaphores {
    public static void main(String[] args) throws Exception {
        Connection.getInstance().connect();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });

            executorService.shutdown();

            executorService.awaitTermination(1, TimeUnit.DAYS);
        }
    }
}
