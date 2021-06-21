package ru.job4j.parking;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class CarParkingTest {
    @Test
    public void whenSpaceForCarNotEnough() {
        ParkedCar car1 = new Ford(1);
        ParkedCar car2 = new Ford(1);
        CarParking shopParking = new ShopParking(1, 1);
        assertTrue(shopParking.add(car1));
        assertFalse(shopParking.add(car2));
    }

    @Test
    public void whenSpaceForTruckOnParkingForSmallCarsNotEnough() {
        ParkedCar car = new Ford(3);
        CarParking shopParking = new ShopParking(1, 0);
        assertFalse(shopParking.add(car));
    }

    @Test
    public void whenSmallCarParked() {
        ParkedCar car = new Ford(1);
        CarParking shopParking = new ShopParking(1, 1);
        shopParking.add(car);
        String parkingSpaceNumber = car.getParkingSpaceNumber().get();
        assertEquals("S1", parkingSpaceNumber);
        assertEquals(car, shopParking.findCarByParkingSpaceNumber(parkingSpaceNumber).get());
    }

    @Test
    public void whenTruckParked() {
        ParkedCar car = new Ford(2);
        CarParking shopParking = new ShopParking(1, 1);
        shopParking.add(car);
        String parkingSpaceNumber = car.getParkingSpaceNumber().get();
        assertEquals("T1", parkingSpaceNumber);
        assertEquals(car, shopParking.findCarByParkingSpaceNumber(parkingSpaceNumber).get());
    }

    @Test
    public void whenTruckParkedOnParkingForSmallCars() {
        ParkedCar smallCar = new Ford(1);
        ParkedCar truck = new Ford(2);
        CarParking shopParking = new ShopParking(3, 0);
        shopParking.add(smallCar);
        shopParking.add(truck);
        String parkingSpaceNumber = truck.getParkingSpaceNumber().get();
        assertEquals("S2S3", parkingSpaceNumber);
        assertEquals(truck, shopParking.findCarByParkingSpaceNumber(parkingSpaceNumber).get());
    }

    @Test
    public void whenRemoveSmallCarFromParking() {
        ParkedCar car = new Ford(1);
        CarParking shopParking = new ShopParking(1, 1);
        shopParking.add(car);
        String parkingSpaceNumber = car.getParkingSpaceNumber().get();
        shopParking.remove(car);
        assertEquals(Optional.empty(), shopParking.findCarByParkingSpaceNumber(parkingSpaceNumber));
    }

    @Test
    public void whenRemoveTruckFromParking() {
        ParkedCar car = new Ford(3);
        CarParking shopParking = new ShopParking(3, 0);
        shopParking.add(car);
        String parkingSpaceNumber = car.getParkingSpaceNumber().get();
        shopParking.remove(car);
        assertEquals(Optional.empty(), shopParking.findCarByParkingSpaceNumber(parkingSpaceNumber));
    }

    @Test
    public void whenRemoveCarAndCarNotExists() {
        ParkedCar car = new Ford(1);
        CarParking shopParking = new ShopParking(1, 1);
        assertFalse(shopParking.remove(car));
    }
}