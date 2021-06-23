package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    //CHECKSTYLE:OFF
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();
    //CHECKSTYLE:ON

    public void put(K key, V value) {
        SoftReference<V> softValue = new SoftReference<>(value);
        cache.put(key, softValue);
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            V content = load(key);
            put(key, content);
        }
        V strongReference = cache.get(key).get();
        if (strongReference == null) {
            V content = load(key);
            put(key, content);
            return get(key);
        }
        return strongReference;
    }

    protected abstract V load(K key);
}
