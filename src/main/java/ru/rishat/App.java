package ru.rishat;

public class App {

    static final Monitor monitor = new Monitor();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    while (monitor.x != 1) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("A");
                    monitor.x = 2;
                    monitor.notifyAll();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    while (monitor.x != 2) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("B");
                    monitor.x = 3;
                    monitor.notifyAll();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    while (monitor.x != 3) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("C");
                    monitor.x = 1;
                    monitor.notifyAll();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();

    }
}

class Monitor {
    int x = 1;
}
