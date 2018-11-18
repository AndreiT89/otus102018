package ru.otus.memory;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashSet;

@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 1000;

        long mem = getMem();
        System.out.println("Mem: " + mem);

        String str = new String();

        long mem2 = getMem();
        System.out.println("Empty String size: " + (mem2 - mem));


        ArrayList<String> list = new ArrayList<>();
        long mem3 = getMem();
        long mem4 = mem3 - mem2;
        long mem5=0;
        System.out.println("Empty ArrayList size: " + (mem4));

        for (int i = 0; i < size; i++) {
            list.add(new String());
            mem5 = getMem();

        }
        System.out.println("ArrayList size : " + (mem5 - mem4));

        long mem6 = getMem();
        HashSet<Integer> set = new HashSet<>();

        long mem7 = getMem() - mem6;
        System.out.println("Empty HashSet size: " + mem7);
        long mem8=0;
        for (int i = 0; i < size; i++) {
            set.add(new Integer(i));
            mem8 = getMem();

        }
        System.out.println("HashSet size : " + (mem8 - mem7));
        System.out.println("HashSet size: " + set.size());
        long mem9 = getMem();
        Integer integer = new Integer(4);
        long mem10 = getMem();
        System.out.println("Integer: " + (mem10 - mem9));
        set = null;
        list = null;
        System.out.println("Array is ready for GC");

        Thread.sleep(1000); //wait for 1 sec
    }

    private static long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
