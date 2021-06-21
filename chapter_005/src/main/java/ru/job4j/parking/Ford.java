package ru.job4j.parking;

import java.util.Optional;

public class Ford implements ParkedCar {
    private final int size;
    private String parkingSpaceNumber;

    public Ford(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setParkingSpaceNumber(String parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    @Override
    public Optional<String> getParkingSpaceNumber() {
        return Optional.ofNullable(parkingSpaceNumber);
    }
}
