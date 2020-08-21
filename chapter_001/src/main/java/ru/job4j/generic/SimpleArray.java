package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] array;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public SimpleArray(int count) {
        array = (T[]) new Object[count];
    }

    public void add(T model) {
        array[size++] = model;
    }

    public void set(int index, T model) {
        int validatedIndex = Objects.checkIndex(index, size);
        array[validatedIndex] = model;
    }

    public void remove(int index) {
        int validatedIndex = Objects.checkIndex(index, size);
        System.arraycopy(array, validatedIndex + 1, array, validatedIndex, size - 1);
        size -= 1;
    }

    public T get(int index) {
        int validatedIndex = Objects.checkIndex(index, size);
        return array[validatedIndex];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int point = 0;
            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[point++];
            }
        };
    }
}
