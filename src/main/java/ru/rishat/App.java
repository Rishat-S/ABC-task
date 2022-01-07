package ru.rishat;

public class App {

    static final Monitor MONITOR = new Monitor();

    public static void main(String[] args) {

        new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                synchronized (MONITOR) {
                    while (MONITOR.x != 1) {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("A");
                    MONITOR.x = 2;
                    MONITOR.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (MONITOR) {
                    while (MONITOR.x != 2) {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("B");
                    MONITOR.x = 3;
                    MONITOR.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (MONITOR) {
                    while (MONITOR.x != 3) {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("C");
                    MONITOR.x = 1;
                    MONITOR.notifyAll();
                }
            }
        }).start();
    }
}

class Monitor {
    int x = 1;
}
