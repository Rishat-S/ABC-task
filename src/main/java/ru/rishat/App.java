package ru.rishat;

public class App {

    static final Monitor monitor = new Monitor();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    if (monitor.x == 1) {
                        System.out.print("A");
                        monitor.x = 2;
                        monitor.notifyAll();
                    } else {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });

        Thread threadB = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    if (monitor.x == 2) {
                        System.out.print("B");
                        monitor.x = 3;
                        monitor.notifyAll();
                    } else {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });

        Thread threadC = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    if (monitor.x == 3) {
                        System.out.print("C");
                        monitor.x = 1;
                        monitor.notifyAll();
                    } else {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

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
