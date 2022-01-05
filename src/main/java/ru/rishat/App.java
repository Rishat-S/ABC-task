package ru.rishat;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Object monitor = new Object();
        List<Thread> threads = new ArrayList<>();
       Thread thread1 = new Thread(new Runnable() {
           @Override
           public void run() {
               for (int i = 0; i < 5; i++) {
                   System.out.print("A");
               }
           }
       });
       Thread thread2 = new Thread(new Runnable() {
           @Override
           public void run() {
               for (int i = 0; i < 5; i++) {
                   System.out.print("B");
               }
           }
       });
       Thread thread3 = new Thread(new Runnable() {
           @Override
           public void run() {
               for (int i = 0; i < 5; i++) {
                   System.out.print("C");

               }
           }
       });

       threads.add(thread1);
       threads.add(thread2);
       threads.add(thread3);

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
