package ru.job4j.parking;

public interface CarParking {

    boolean add(Car car);

    boolean remove(Car car);

    Car findCarByParkingSpaceNumber(String spaceNumber);
}
