package ru.otus.memory;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;

@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {
    private static int size = 20_000_000;
    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());
        measureString(new char[1]);
        measureString(new char[10]);
        measureCollection(new Integer(1));
        measureCollection(new Boolean(true));
    }

    private static long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static void measureString(char[] strArg) throws InterruptedException {
        long mem = getMem();
        System.out.println("Mem: " + mem);

        String[] array = new String[size];
        long mem2 = getMem();
        System.out.println("Empty array size: " + (mem2 - mem)/array.length);
        for (int i = 0; i < array.length; i++) {
            array[i] = new String(strArg);
        }
        long mem3 = getMem();
        System.out.println("Element size: " + (mem3 - mem2) / array.length);
        array = null;
        System.out.println("Array is ready for GC");

        Thread.sleep(1000);
    }

    private static void measureCollection(Object element) throws InterruptedException {
        long mem = getMem();
        System.out.println("Mem: " + mem);
        ArrayList<Object> list = new ArrayList<>();
        long mem2 = getMem();
        System.out.println("Empty List size: " + (mem2 - mem));
        for (int i = 0; i < size; i++) {
            list.add(element);
        }
        long mem3 = getMem();
        System.out.println("Element size: " + (mem3 - mem2) / list.size());
        list = null;
        System.out.println("List is ready for GC");

        Thread.sleep(1000);
    }
}
