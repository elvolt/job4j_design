package ru.job4j.parking;

import java.util.Optional;

public interface CarParking {

    boolean add(ParkedCar car);

    boolean remove(ParkedCar car);

    Optional<ParkedCar> findCarByParkingSpaceNumber(String spaceNumber);
}
