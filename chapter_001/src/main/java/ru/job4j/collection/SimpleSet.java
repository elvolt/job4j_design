package ru.job4j.collection;

import java.util.*;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> set = new SimpleArray<>();

    public boolean contains(E value) {
        for (E item : set) {
            if (Objects.equals(item, value)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(E value) {
        for (int i = 0; i < set.size(); i++) {
            if (contains(value)) {
                return false;
            }
        }
        return set.add(value);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }
}
