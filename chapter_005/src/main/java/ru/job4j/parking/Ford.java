package ru.job4j.parking;

public class Ford implements Car {
    private final int size;

    public Ford(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setParkingSpaceNumber(String parkingSpaceNumber) {

    }

    @Override
    public String getParkingSpaceNumber() {
        return null;
    }
}
