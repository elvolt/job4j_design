package ru.job4j.io.serialization;

public class Engine {
    private final String type;
    private final int year;

    public Engine(String type, int year) {
        this.type = type;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "type='" + type + '\''
                + ", year=" + year
                + '}';
    }
}
