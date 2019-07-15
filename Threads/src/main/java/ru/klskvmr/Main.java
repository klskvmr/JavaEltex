package ru.klskvmr;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
        for (int i = 0; i < 10; i++) {
            Runnable r = new MyThread(i, new Printer());
            Thread t = new Thread(r);
            t.start();
        }

        Thread.sleep(1000);
        System.out.println("a = " + Printer.a);
         */

        Printer printer = new Printer();

        Runnable[] runnables = new Runnable[10];

        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            Runnable r = new MyThread(i, printer);
            Thread t = new Thread(r);
            t.start();

            runnables[i] = new MyThread(i, printer);
            exec.submit(runnables[i]);
        }

        Thread.sleep(2000);
        exec.shutdown();
        System.out.println("a = " + Printer.a);
    }
}