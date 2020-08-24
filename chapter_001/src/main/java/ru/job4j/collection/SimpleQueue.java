package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in.size() == 0) {
            throw new NoSuchElementException();
        }
        while (in.size() > 0) {
            out.push(in.pop());
        }
        T result = out.pop();
        while (out.size() > 0) {
            in.push(out.pop());
        }
        return result;
    }

    public void push(T value) {
        in.push(value);
    }
}
