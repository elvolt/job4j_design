package ru.job4j.menu;

import java.util.List;
import java.util.Optional;

public interface IMenu {
    List<IItem> getItems();

    void addItem(IItem item);

    boolean addSubItem(IItem parent, IItem child);

    Optional<IItem> getItem(IItem item);

    boolean removeItem(IItem item);

    void action(IItem item);
}
