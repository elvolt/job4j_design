package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    private <T> T findItemBy(List<T> collection, BiPredicate<T, T> predicate) {
        T result = collection.get(0);
        for (T item : collection) {
            if (predicate.test(item, result)) {
                result = item;
            }
        }
        return result;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findItemBy(value, (a, b) -> comparator.compare(a, b) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findItemBy(value, (a, b) -> comparator.compare(a, b) < 0);
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