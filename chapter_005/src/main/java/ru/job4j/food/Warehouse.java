package ru.job4j.food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Warehouse implements Storage {
    private final List<Food> storage = new ArrayList<>();
    private Predicate<Food> deliveryCondition =
            food -> food.getPeriodToExpirationDateInPercent() >= 75;

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
