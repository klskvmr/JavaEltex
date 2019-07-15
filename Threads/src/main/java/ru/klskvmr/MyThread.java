package ru.klskvmr;

public class MyThread implements Runnable {
    private Integer number;
    private Printer printer;

    public void run() {
        System.out.println(this.number);
        printer.increment();
    }

    public MyThread(Integer number, Printer printer) {
        this.number = number;
        this.printer = printer;
    }
}
