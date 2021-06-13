package ru.job4j.parking;

public interface Car {

    int getSize();

    void park(CarParking parking);

    void setParkingSpaceNumber(String parkingSpaceNumber);

    String getParkingSpaceNumber();
}
