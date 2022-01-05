package ru.rishat;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    System.out.print("A");
                }
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(() -> {
            synchronized (monitor) {
                System.out.print("B");
            }
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread threadC = new Thread(() -> {
            synchronized (monitor) {
                System.out.print("C");
            }
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        threadA.start();
        threadB.start();
        threadC.start();

    }
}
