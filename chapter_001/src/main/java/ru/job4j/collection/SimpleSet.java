package ru.job4j.collection;

import java.util.*;

public class SimpleSet<T> implements Iterable<T> {
    private final SimpleArray<T> set = new SimpleArray<>();

    public boolean add(T value) {
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i).equals(value)) {
                return false;
            }
        }
        return set.add(value);
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
