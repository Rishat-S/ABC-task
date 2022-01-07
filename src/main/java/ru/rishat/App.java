package ru.rishat;

import static ru.rishat.App.A;

public class App {

    public static final int COUNT = 5;
    static final Monitor MONITOR = new Monitor();
    static final String A = "A";
    static final String B = "B";
    static final String C = "C";

    public static void main(String[] args) {

        threadX(A, B);

        threadX(B, C);

        threadX(C, A);
    }

    private static void threadX(String currentLetter, String nextLetter) {
        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                synchronized (MONITOR) {
                    while (!MONITOR.nextLetter.equals(currentLetter)) {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(currentLetter);
                    MONITOR.nextLetter = nextLetter;
                    MONITOR.notifyAll();
                }
            }
        }).start();
    }
}

class Monitor {
    String nextLetter = A;
}
