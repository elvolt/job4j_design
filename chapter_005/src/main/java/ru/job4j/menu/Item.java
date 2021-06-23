package ru.job4j.menu;

import java.util.List;
import java.util.Optional;

public interface Item {
    String getName();

    List<Item> getSubItems();

    boolean addSubItem(Item item);

    boolean removeSubItem(Item item);

    Optional<Item> getParent();

    void setParent(Item parent);
}
