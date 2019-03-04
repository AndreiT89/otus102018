package ru.cachehomework;

public class Main {
    //Launch with VM options -Xmx2m -Xms2m
    public static void main(String[] args) throws InterruptedException {
        int maxIterations = 5000;
        CacheEngine<Integer, String> cache = new CacheEngineImpl<>(2000, 0 , 0);
        for (int i = 0; i < maxIterations; i++) {

            cache.put(new CacheElement<>(i, "String" + i));

        }

        for (int i = 0; i < maxIterations; i++) {
            CacheElement<Integer, String> element = cache.get(i);
            System.out.println("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }
        System.out.println("Cache hits: " + cache.getHitCount());
        System.out.println("Cache misses: " + cache.getMissCount());
        Thread.sleep(1000);
        for (int i = 0; i < maxIterations; i++) {
            CacheElement<Integer, String> element = cache.get(i);
            System.out.println("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }
        System.out.println("Cache hits: " + cache.getHitCount());
        System.out.println("Cache misses: " + cache.getMissCount());
    }
}
