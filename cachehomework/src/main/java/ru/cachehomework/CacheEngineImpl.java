package ru.cachehomework;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.lang.ref.SoftReference;

public class CacheEngineImpl<K, V> implements CacheEngine<K, V> {
    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final Map<K, SoftReference<CacheElement<K, V>>> elements = new HashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public CacheEngineImpl(int maxElements, long lifeTimeMs, long idleTimeMs) {
        this.maxElements = maxElements;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
    }

    @Override
    public void put(CacheElement<K, V> element) {
        if (elements.size() == maxElements) {
            long currTime = System.currentTimeMillis();
            elements.entrySet().removeIf(entry ->
                   entry.getValue() == null || currTime - entry.getValue().get().getLastAccessTime() >= idleTimeMs || currTime - entry.getValue().get().getCreationTime() >= lifeTimeMs
            );
        }
        K key = element.getKey();
        elements.put(key, new SoftReference<>(element));

    }

    @Override
    public CacheElement<K, V> get(K key) {
        CacheElement<K, V> element = null;
        try {
            element = elements.get(key).get();


            if (element != null) {
                hit++;
                element.setAccessed();
            } else {
                miss++;
            }
        } catch (NullPointerException npe) {
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
