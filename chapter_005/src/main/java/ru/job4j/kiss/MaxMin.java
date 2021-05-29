package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMin {
    private <T> List<T> getSortedCollection(List<T> collection, Comparator<T> comparator) {
        List<T> collectionCopy = new ArrayList<>(collection);
        for (int out = collectionCopy.size() - 1; out > 0; out--) {
            for (int in = 0; in < out; in++) {
                if (comparator.compare(collectionCopy.get(in), collectionCopy.get(in + 1)) > 0) {
                    swap(collectionCopy, in, in + 1);
                }
            }
        }
        return collectionCopy;
    }

    private <T> void swap(List<T> collection, int index1, int index2) {
        T tmp = collection.get(index2);
        collection.set(index2, collection.get(index1));
        collection.set(index1, tmp);
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getSortedCollection(value, comparator).get(value.size() - 1);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getSortedCollection(value, comparator).get(0);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(-7);
        list.add(-11);
        list.add(0);
        list.add(21);
        list.add(31);
        list.add(13);

        System.out.println(new MaxMin().max(list, Comparator.comparingInt(i -> i)));
        System.out.println(new MaxMin().min(list, Comparator.comparingInt(i -> i)));
    }
}