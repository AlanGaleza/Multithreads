package com.multithreads.multithreads.CallableAndFuture;

import java.io.IOException;
import java.util.concurrent.*;

public class CallableAndFuture {

    public static void main(String[] args) {
        ExecutorService executor= Executors.newCachedThreadPool();

        Future<?> future = executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {

                int duration = ThreadLocalRandom.current().nextInt(4000);

                if (duration > 2000) {
                    throw new IOException("Sleeping for too long.");
                }
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Starting ...");
                System.out.println("Finished");
                return null;
            }
        });

        executor.shutdown();

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
