package com.multithreads.multithreads.Semaphores;

import java.util.concurrent.*;

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
