package ru.eltex.koloskova;

import java.util.HashMap;
import java.util.HashSet;

public class TimeTest1 {
    public static void add1000000(HashSet<Integer> hashSet, HashMap<Integer, Integer> hashMap) {
        System.out.println("ADD 1000000:");

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            hashSet.add(i);
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("HashSet time: " + elapsedTime);

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            hashMap.put(i, i);
        }
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("HashMap time: " + elapsedTime);
        System.out.println();
    }

    public static void remove1(HashSet<Integer> hashSet, HashMap<Integer, Integer> hashMap) {
        System.out.println("REMOVE 1:");

        long startTime = System.nanoTime();
        hashSet.remove(1);
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        System.out.println("HashSet time: " + elapsedTime);

        startTime = System.nanoTime();
        hashMap.remove(1, 1);
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("HashMap time: " + elapsedTime);
        System.out.println();
    }
}
