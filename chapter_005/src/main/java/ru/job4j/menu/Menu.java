package ru.job4j.menu;

import java.util.List;
import java.util.Optional;

public interface Menu {
    List<Item> getItems();

    void addItem(Item item);

    boolean addSubItem(Item parent, Item child);

    Optional<Item> getItem(Item item);

    boolean removeItem(Item item);

    void action(Item item);
}
