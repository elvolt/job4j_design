package ru.job4j.food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Shop implements Storage {
    private final List<Food> storage = new ArrayList<>();
    private int discountInPercent = 50;
    private Predicate<Food> deliveryCondition =
            food -> {
                double periodToExpirationDateInPercent = food.getPeriodToExpirationDateInPercent();
                return periodToExpirationDateInPercent > 0 && periodToExpirationDateInPercent < 75;
            };

    @Override
    public void add(Food food) {
        if (food.getPeriodToExpirationDateInPercent() <= 25) {
            food.setDiscount(discountInPercent);
        }
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

    public int getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(int discountInPercent) {
        this.discountInPercent = discountInPercent;
    }
}
