package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShopParking implements CarParking {
    private final ParkedCar[] smallCarsParking;
    private final ParkedCar[] trucksParking;
    private final int smallCarsParkingSize;
    private final int trucksParkingSize;
    private int smallCarsPlacesBusy = 0;
    private int trucksPlacesBusy = 0;

    public ShopParking(int spacesForSmallCars, int spacesForTrucks) {
        smallCarsParkingSize = spacesForSmallCars;
        trucksParkingSize = spacesForTrucks;
        smallCarsParking = new ParkedCar[spacesForSmallCars];
        trucksParking = new ParkedCar[spacesForTrucks];
    }

    @Override
    public boolean add(ParkedCar car) {
        if (car.getSize() == 1) {
            return addSmallCar(car);
        }
        return addTruck(car);
    }

    private boolean addSmallCar(ParkedCar car) {
        if (smallCarsPlacesBusy == smallCarsParkingSize) {
            return false;
        }
        int index = findFreeSpace(smallCarsParking);
        smallCarsParking[index] = car;
        smallCarsPlacesBusy++;
        car.setParkingSpaceNumber("S" + (index + 1));
        return true;
    }

    private int findFreeSpace(ParkedCar[] parking) {
        for (int i = 0; i < parking.length; i++) {
            if (parking[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private List<Integer> findFreeSpace(ParkedCar[] parking, int carSize) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < parking.length; i++) {
            if (parking[i] == null) {
                result.add(i);
            }
            if (result.size() == carSize) {
                return result;
            }
            if (parking[i] != null) {
                result.clear();
            }
        }
        return result;
    }

    private boolean addTruck(ParkedCar car) {
        if (trucksPlacesBusy < trucksParkingSize) {
            int index = findFreeSpace(trucksParking);
            trucksParking[index] = car;
            trucksPlacesBusy++;
            car.setParkingSpaceNumber("T" + (index + 1));
            return true;
        }
        if (smallCarsPlacesBusy + car.getSize() <= smallCarsParkingSize) {
            List<Integer> spaces = findFreeSpace(smallCarsParking, car.getSize());
            String parkingSpaceNumber = spaces.stream()
                    .map(i -> "S" + (i + 1))
                    .collect(Collectors.joining());
            spaces.forEach(space -> smallCarsParking[space] = car);
            smallCarsPlacesBusy += car.getSize();
            car.setParkingSpaceNumber(parkingSpaceNumber);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(ParkedCar car) {
        if (car.getSize() == 1) {
            return removeSmallCar(car);
        }
        return removeTruck(car);
    }

    private boolean removeSmallCar(ParkedCar car) {
        if (car.getParkingSpaceNumber().isEmpty()) {
            return false;
        }
        if (findCarByParkingSpaceNumber(car.getParkingSpaceNumber().get()).isEmpty()) {
            return false;
        }
        if (!findCarByParkingSpaceNumber(car.getParkingSpaceNumber().get()).get().equals(car)) {
            return false;
        }
        int spaceNumber = Integer.parseInt(car.getParkingSpaceNumber().get().substring(1));
        smallCarsParking[spaceNumber - 1] = null;
        return true;
    }

    private boolean removeTruck(ParkedCar car) {
        if (car.getParkingSpaceNumber().isEmpty()) {
            return false;
        }
        if (findCarByParkingSpaceNumber(car.getParkingSpaceNumber().get()).isEmpty()) {
            return false;
        }
        if (!findCarByParkingSpaceNumber(car.getParkingSpaceNumber().get()).get().equals(car)) {
            return false;
        }
        int fromIndex = Integer.parseInt(car.getParkingSpaceNumber().get().substring(1, 2)) - 1;
        IntStream.of(fromIndex, fromIndex - 1 + car.getSize())
                .forEach(i -> smallCarsParking[i] = null);
        return true;
    }

    @Override
    public Optional<ParkedCar> findCarByParkingSpaceNumber(String parkingSpaceNumber) {
        if (parkingSpaceNumber.startsWith("S")) {
            return Optional.ofNullable(
                    smallCarsParking[Integer.parseInt(parkingSpaceNumber.substring(1, 2)) - 1]
            );
        }
        if (parkingSpaceNumber.startsWith("T")) {
            return Optional.ofNullable(
                    trucksParking[Integer.parseInt(parkingSpaceNumber.substring(1)) - 1]
            );
        }
        return Optional.empty();
    }

}
