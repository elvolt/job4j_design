package ru.job4j.food;

import java.time.LocalDate;

public class Milk extends Food {
    public Milk(String name, LocalDate expiryDate, LocalDate createDate, String price) {
        super(name, expiryDate, createDate, price);
    }
}
