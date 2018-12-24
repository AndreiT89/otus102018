package ru.otus.memory;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;

@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {

    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());
        Measurer.measureString(new char[1]);
        Measurer.measureString(new char[10]);
        Measurer.measureCollection(()->new Integer(1));
        Measurer.measureCollection(()->new Boolean(true));
    }


}
