package ru.job4j.food;

import java.util.List;
import java.util.function.Predicate;

public interface Storage {
    void add(Food food);

    List<Food> getStorage();

    Predicate<Food> getDeliveryCondition();

    void setDeliveryCondition(Predicate<Food> deliveryCondition);
}