package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<V> {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table;
    private int capacity = DEFAULT_INITIAL_CAPACITY;
    private int size = 0;
    private int modCount = 0;

    static class Node<K, V> {
        private final int hash;
        private final K key;
        private V value;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public final K getKey()        {
            return key;
        }

        public final V getValue()      {
            return value;
        }
    }

    private int hash(Object key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    private int getIndex(K key) {
        return (capacity - 1) & hash(key);
    }

    private Node<K, V>[] resize() {
        Node<K, V>[] newTable;
        if (table == null) {
            capacity = DEFAULT_INITIAL_CAPACITY;
            newTable = (Node<K, V>[]) new Node[capacity];
        } else {
            capacity = capacity << 1;
            newTable = (Node<K, V>[]) new Node[capacity];
            for (Node<K, V> node : table) {
                if (node != null) {
                    int index = getIndex(node.getKey());
                    newTable[index] = node;
                }
            }
        }
        return newTable;
    }

    public boolean insert(K key, V value) {
        if (table == null || size > DEFAULT_LOAD_FACTOR * capacity) {
            table = resize();
        }
        Node<K, V> n = new Node<>(hash(key), key, value);
        int index = getIndex(key);
        if (table[index] != null) {
            return false;
        }
        table[index] = n;
        modCount++;
        size++;
        return true;
    }

    public V get(K key) {
        int index = getIndex(key);
        int validatedIndex = Objects.checkIndex(index, size);
        if (table[validatedIndex] == null || !(table[validatedIndex].getKey().equals(key))) {
            return null;
        }
        return table[validatedIndex].getValue();
    }

    public boolean delete(K key) {
        int index = getIndex(key);
        int validatedIndex = Objects.checkIndex(index, size);
        if (table[validatedIndex] == null || !(table[validatedIndex].getKey().equals(key))) {
            return false;
        }
        table[validatedIndex] = null;
        modCount++;
        size--;
        return true;
    }

    @Override
    public Iterator<V> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (size == 0) {
                    return false;
                }
                for (int i = point; i <= size; i++) {
                    if (table[i] != null) {
                        point = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public V next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].getValue();
            }
        };
    }
}
