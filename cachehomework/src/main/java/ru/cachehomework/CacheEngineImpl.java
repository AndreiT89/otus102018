package ru.cachehomework;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.lang.ref.SoftReference;

public class CacheEngineImpl<K, V> implements CacheEngine<K, V> {
    private final int maxElements;
    private final long idleTimeMs;
    private final Map<K, SoftReference<CacheElement<K, V>>> elements = new LinkedHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public CacheEngineImpl(int maxElements, long idleTimeMs) {
        this.maxElements = maxElements;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
    }

    @Override
    public void put(CacheElement<K,V> element) {
//        if (elements.size() == maxElements) {
//            while (elements.keySet().iterator().hasNext()){
//                K firstkey = elements.keySet().iterator().next();
//                CacheElement<K,V> oldElement = elements.get(firstkey).get();
//                if (oldElement.getLastAccessTime()>=idleTimeMs){
//                    System.gc();
//                }
//
//            }
//
//        }

        K key = element.getKey();
        elements.put(key, new SoftReference<>(element));
    }

    @Override
    public CacheElement<K, V> get(K key) {
        CacheElement<K, V> element = elements.get(key).get();
        if (element != null) {
            hit++;
            element.setAccessed();
        } else {
            miss++;
        }
        return element;
    }

    @Override
    public int getHitCount() {
        return hit;
    }

    @Override
    public int getMissCount() {
        return miss;
    }

    @Override
    public void dispose() {

    }
}
