package ru.job4j.parking;

import java.util.Optional;

public interface ParkedCar extends Car {
    void setParkingSpaceNumber(String parkingSpaceNumber);

    Optional<String> getParkingSpaceNumber();
}
