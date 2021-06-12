package ru.job4j.food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Trash implements Storage {
    private final List<Food> storage = new ArrayList<>();
    private Predicate<Food> deliveryCondition =
           food -> food.getPeriodToExpirationDateInPercent() <= 0;

    @Override
    public void add(Food food) {
        storage.add(food);
    }

    @Override
    public List<Food> getStorage() {
        return storage;
    }

    @Override
    public Predicate<Food> getDeliveryCondition() {
        return deliveryCondition;
    }

    @Override
    public void setDeliveryCondition(Predicate<Food> deliveryCondition) {
        this.deliveryCondition = deliveryCondition;
    }
}
