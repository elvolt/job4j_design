package ru.job4j.it;

import java.util.*;

public class EvenIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenIterator(int[] data) {
        this.data = data;
    }

    public static int getNextEvenNumPoint(int cntPoint, int[] nums) {
        int rsl = -1;
        for (int i = cntPoint; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean hasNext() {
        return getNextEvenNumPoint(point, data) != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int cntPoint = getNextEvenNumPoint(point, data);
        point = cntPoint + 1;
        return data[cntPoint];
    }
}
