package ru.job4j.food;

import java.time.LocalDate;

public class Bread extends Food {
    public Bread(String name, LocalDate expiryDate, LocalDate createDate, String price) {
        super(name, expiryDate, createDate, price);
    }
}
