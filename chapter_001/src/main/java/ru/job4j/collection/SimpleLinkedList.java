package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        private T item;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }

    public void add(T value) {
        Node<T> cntLast = last;
        Node<T> newNode = new Node<>(value, null);
        last = newNode;
        if (cntLast == null) {
            first = newNode;
        } else {
            cntLast.next = newNode;
        }
        size += 1;
        modCount += 1;
    }

    public T get(int index) {
        int validatedIndex = Objects.checkIndex(index, size);
        Node<T> result = first;
        for (int i = 0; i < validatedIndex; i++) {
            result = result.next;
        }
        return result.item;
    }

    public T deleteFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        T result = first.item;
        if (first.next == null) {
            first = null;
            last = null;
        } else {
            Node<T> cntFirst = first;
            first = first.next;
            cntFirst.next = null;
        }
        size -= 1;
        modCount += 1;
        return result;
    }

    public T deleteLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        T result = last.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            int i = 1;
            Node<T> newLast = first;
            while (i != size - 1) {
                newLast = newLast.next;
                i++;
            }
            last = newLast;
            last.next = null;
        }
        size -= 1;
        modCount += 1;
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            private Node<T> lastReturned;
            private Node<T> nextNode;
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
                if (point == 0) {
                    nextNode = first;
                }
                lastReturned = nextNode;
                nextNode = lastReturned.next;
                point += 1;
                return lastReturned.item;
            }
        };
    }
}
