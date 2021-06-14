package ru.job4j.tdd;

import ru.job4j.parking.Car;
import ru.job4j.parking.CarParking;

public class ShopParking implements CarParking {
    private final int spacesForSmallCars;
    private final int spacesForTrucks;

    public ShopParking(int spacesForSmallCars, int spacesForTrucks) {
        this.spacesForSmallCars = spacesForSmallCars;
        this.spacesForTrucks = spacesForTrucks;
    }

    @Override
    public boolean add(Car car) {
        return false;
    }

    @Override
    public boolean remove(Car car) {
        return false;
    }

    @Override
    public Car findCarByParkingSpaceNumber(String parkingSpaceNumber) {
        return null;
    }
}
