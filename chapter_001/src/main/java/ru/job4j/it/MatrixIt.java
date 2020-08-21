package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    public int getNextNotEmptyRow(int cntRow, int[][] data) {
        int rsl = -1;
        for (int i = cntRow; i < data.length; i++) {
            if (data[i].length > 0) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean hasNext() {
        int notEmptyRow = getNextNotEmptyRow(row, data);
        return notEmptyRow != -1 && column <= data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int cntRow = row;
        int cntColumn = column;
        if (data[cntRow].length == 0) {
            cntRow = getNextNotEmptyRow(cntRow, data);
            cntColumn = 0;
        }
        if (data[cntRow].length - 1 == column) {
            if (hasNext()) {
                row = getNextNotEmptyRow(cntRow + 1, data);
                column = 0;
            } else {
                row += 1;
            }
        } else {
            column += 1;
        }
        return data[cntRow][cntColumn];
    }
}