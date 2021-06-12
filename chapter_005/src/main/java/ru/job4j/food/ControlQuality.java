package ru.job4j.food;

import java.util.List;
import java.util.Optional;

public class ControlQuality {
    private final List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    private Optional<Storage> checkStorageForFood(Food food) {
        return storages.stream()
                .filter(storage -> storage.getDeliveryCondition()
                        .test(food))
                .findFirst();
    }

    public void distribute(Food food) {
        Optional<Storage> storage = checkStorageForFood(food);
        if (storage.isEmpty()) {
            throw new IllegalStateException("Storage for food not found");
        }
        storage.get().add(food);
    }
}
