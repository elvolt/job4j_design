package ru.job4j.menu;

import java.util.List;

public interface IItem {
    String getName();

    List<IItem> getSubItems();
}
