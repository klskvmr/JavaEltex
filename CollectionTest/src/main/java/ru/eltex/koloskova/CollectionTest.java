package ru.eltex.koloskova;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class CollectionTest {
    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        TimeTest1.add1000000(hashSet, hashMap);
        TimeTest1.remove1(hashSet, hashMap);

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        TreeSet<Integer> treeSet = new TreeSet<>();


        TimeTest2.add1000000(linkedList, arrayList, treeSet);
        TimeTest2.remove1(linkedList, arrayList, treeSet);

        //getClassName(arrayList);
        //getClassName(linkedList);
        //getClassName(treeSet);
    }

    private static void getClassName(ArrayList<Integer> arrayList) {
        Class arrayInfo = arrayList.getClass();
        System.out.println("Class name: " + arrayInfo.getName());

        Field[] fields = arrayInfo.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        Method[] methods = arrayInfo.getMethods(); //arrayList.getClass().getMethods()
        for (Method method : methods) {
            System.out.print(method.getName() + " ");
        }

        System.out.println();
        System.out.println();
    }
    /*

    private static void getClassName(LinkedList<Integer> linkedList) {
        System.out.println("Class name: " + linkedList.getClass().getName());
    }

    private static void getClassName(ArrayDeque<Integer> arrayDeque) {
        System.out.println("Class name: " + arrayDeque.getClass().getName());
    }

    private static void getClassName(TreeSet<Integer> treeSet) {
        System.out.println("Class name: " + treeSet.getClass().getName());
    }
     */

}