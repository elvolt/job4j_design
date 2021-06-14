package ru.job4j.parking;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tdd.ShopParking;

import static org.junit.Assert.*;

public class CarParkingTest {
    @Ignore
    @Test
    public void whenSpaceForCarNotEnough() {
        Car car1 = new Ford(1);
        Car car2 = new Ford(1);
        CarParking shopParking = new ShopParking(1, 1);
        assertTrue(shopParking.add(car1));
        assertFalse(shopParking.add(car2));
    }

    @Ignore
    @Test
    public void whenSpaceForTruckOnParkingForSmallCarsNotEnough() {
        Car car = new Ford(3);
        CarParking shopParking = new ShopParking(1, 0);
        assertFalse(shopParking.add(car));
    }

    @Ignore
    @Test
    public void whenSmallCarParked() {
        Car car = new Ford(1);
        CarParking shopParking = new ShopParking(1, 1);
        shopParking.add(car);
        String parkingSpaceNumber = car.getParkingSpaceNumber();
        assertEquals("S1", parkingSpaceNumber);
        assertEquals(car, shopParking.findCarByParkingSpaceNumber(parkingSpaceNumber));
    }

    @Ignore
    @Test
    public void whenTruckParked() {
        Car car = new Ford(2);
        CarParking shopParking = new ShopParking(1, 1);
        shopParking.add(car);
        String parkingSpaceNumber = car.getParkingSpaceNumber();
        assertEquals("T1", parkingSpaceNumber);
        assertEquals(car, shopParking.findCarByParkingSpaceNumber(parkingSpaceNumber));
    }

    @Ignore
    @Test
    public void whenTruckParkedOnParkingForSmallCars() {
        Car car = new Ford(2);
        CarParking shopParking = new ShopParking(2, 0);
        shopParking.add(car);
        String parkingSpaceNumber = car.getParkingSpaceNumber();
        assertEquals("S1S2", parkingSpaceNumber);
        assertEquals(car, shopParking.findCarByParkingSpaceNumber(parkingSpaceNumber));
    }

    @Ignore
    @Test
    public void whenRemoveCarFromParking() {
        Car car = new Ford(1);
        CarParking shopParking = new ShopParking(1, 1);
        shopParking.add(car);
        String parkingSpaceNumber = car.getParkingSpaceNumber();
        shopParking.remove(car);
        assertNull(shopParking.findCarByParkingSpaceNumber(parkingSpaceNumber));
    }

    @Ignore
    @Test
    public void whenRemoveCarAndCarNotExists() {
        Car car = new Ford(1);
        CarParking shopParking = new ShopParking(1, 1);
        assertFalse(shopParking.remove(car));
    }
}