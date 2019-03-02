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
            while (elements.entrySet().iterator().hasNext()) {
                Map.Entry oldEntry = elements.entrySet().iterator().next();
                SoftReference<CacheElement<K, V>> oldElement = null;
                try {
                    oldElement = (SoftReference<CacheElement<K, V>>) oldEntry.getValue();
                    if (oldElement.get().getLastAccessTime() >= idleTimeMs || oldElement.get().getCreationTime() >= lifeTimeMs) {
                        elements.remove(oldEntry.getKey());
                    }
                } catch (NullPointerException npe) {
                    elements.remove(oldEntry.getKey());

                }
            }
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
