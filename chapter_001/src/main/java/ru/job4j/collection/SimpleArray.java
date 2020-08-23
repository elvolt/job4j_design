package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] array;
    private int size = 0;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public SimpleArray() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    private void checkSize() {
        if (size >= DEFAULT_SIZE && size % DEFAULT_SIZE == 0) {
            array = Arrays.copyOf(array, size + 10);
        }
    }

    public T get(int index) {
        int validatedIndex = Objects.checkIndex(index, size);
        return array[validatedIndex];
    }

    public void add(T model) {
        modCount += 1;
        array[size] = model;
        size += 1;
        checkSize();
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[point++];
            }
        };
    }
}