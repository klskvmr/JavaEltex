package ru.eltex.koloskova;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

public class TimeTest2 {
    public static void add1000000(LinkedList<Integer> linkedList, ArrayList<Integer> arrayList,
                                  TreeSet<Integer> treeSet) {
        System.out.println("ADD 1000000:");

        long startTime = System.currentTimeMillis();
        for (Integer i = 0; i < 1000000; i++) {
            linkedList.add(i);
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("LinkedList time: " + elapsedTime);

        startTime = System.currentTimeMillis();
        for (Integer i = 0; i < 1000000; i++) {
            arrayList.add(i);
        }
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("ArrayList time: " + elapsedTime);

        startTime = System.currentTimeMillis();
        for (Integer i = 0; i < 1000000; i++) {
            treeSet.add(i);
        }
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("TreeSet time: " + elapsedTime);

        System.out.println();
    }

    public static void remove1(LinkedList<Integer> linkedList, ArrayList<Integer> arrayList,
                               TreeSet<Integer> treeSet) {
        System.out.println("REMOVE 1:");

        long startTime = System.nanoTime();
        linkedList.remove();
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        System.out.println("LinkedList time: " + elapsedTime);


        startTime = System.nanoTime();
        arrayList.remove(999999);
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("ArrayList time: " + elapsedTime);

        startTime = System.nanoTime();
        treeSet.remove(999999);
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("TreeSet time: " + elapsedTime);

        System.out.println();
    }
}
