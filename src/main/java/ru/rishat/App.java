package ru.rishat;

import java.util.concurrent.*;

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        executorService.execute(() -> {
            try {
                while (true) {
                    System.out.print(".");
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Future<String> futureName = executorService.submit(() -> {
            Thread.sleep(5000);
            return "John";
        });
        Future<Integer> futureAge = executorService.submit(() -> {
            Thread.sleep(5000);
            return 43;
        });

        try {
            String name = futureName.get();
            Integer age = futureAge.get();
            System.out.println("\n" + name + " - age:" + age);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
