package ru.otus.memory;

import java.util.ArrayList;
import java.util.function.Supplier;

public class Measurer {
    private static int size = 20_000_000;
    private static long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static void measureString(char[] strArg) throws InterruptedException {
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

    public static void measureCollection(Supplier<Object> element) throws InterruptedException {
        long mem = getMem();
        System.out.println("Mem: " + mem);
        ArrayList<Object> list = new ArrayList<>();
        long mem2 = getMem();
        System.out.println("Empty List size: " + (mem2 - mem));
        for (int i = 0; i < size; i++) {
            list.add(element.get());
        }
        long mem3 = getMem();
        System.out.println("Element size: " + (mem3 - mem2) / list.size());
        list = null;
        System.out.println("List is ready for GC");

        Thread.sleep(1000);
    }
}
