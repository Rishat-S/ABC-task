package ru.rishat;

import static ru.rishat.App.A;

public class App {

    static final Monitor MONITOR = new Monitor();
    static final String A = "A";
    static final String B = "B";
    static final String C = "C";

    public static void main(String[] args) {

        threadX(A, B);

        threadX(B, C);

        threadX(C, A);
    }

    private static void threadX(String a, String b) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (MONITOR) {
                    while (!MONITOR.nextLetter.equals(a)) {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(a);
                    MONITOR.nextLetter = b;
                    MONITOR.notifyAll();
                }
            }
        }).start();
    }
}

class Monitor {
    String nextLetter = A;
}
