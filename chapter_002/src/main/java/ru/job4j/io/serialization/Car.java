package ru.job4j.io.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final String mark;
    private final int year;
    private final boolean accident;
    private final Engine engine;
    private final Person[] owners;

    public Car(String mark, int year, boolean accident, Engine engine, Person[] owners) {
        this.mark = mark;
        this.year = year;
        this.accident = accident;
        this.engine = engine;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "mark='" + mark + '\''
                + ", year=" + year
                + ", accident=" + accident
                + ", engine=" + engine
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }

    public static void main(String[] args) {
        final Car bmw = new Car(
                "BMW", 2017, false, new Engine("V8", 2017),
                new Person[]{new Person("Fill", 32), new Person("Marry", 23)}
                );

        final Gson gson = new GsonBuilder().create();
        String carJson = gson.toJson(bmw);
        System.out.println(carJson);
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
