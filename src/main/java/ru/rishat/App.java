package ru.rishat;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {

    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
        new Thread(() -> {
            int i =0;
            while (true) {
                System.out.println("Counter: " + i++);
                Runnable task =null;
                try {
                    task = blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Thread(task).start();
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            blockingQueue.add(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("___" + index);
            });
        }
    }
}
