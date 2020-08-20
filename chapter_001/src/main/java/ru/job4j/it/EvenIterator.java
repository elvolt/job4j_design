package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class EvenIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenIterator(int[] data) {
        this.data = data;
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static List<Integer> getEvenNums(int[] nums) {
        return Arrays.stream(nums)
                .filter(EvenIterator::isEven)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return point < getEvenNums(data).size();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return getEvenNums(data).get(point++);
    }
}
