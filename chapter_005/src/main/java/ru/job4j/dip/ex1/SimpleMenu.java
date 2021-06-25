package ru.job4j.dip.ex1;

import ru.job4j.menu.Item;
import ru.job4j.menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    Нарушение принципа DI - поле items - мы зависим от реализации, а не абстракции.
    Решение - выделение абстракции для хранилища и уже далее от него нужно будет
    реализовать InMemoryMenuStore
 */

public class SimpleMenu implements Menu {
    private final List<Item> items = new ArrayList<>();

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public boolean addSubItem(Item parent, Item child) {
        return false;
    }

    @Override
    public Optional<Item> getItem(Item item) {
        return Optional.empty();
    }

    @Override
    public boolean removeItem(Item item) {
        return false;
    }

    @Override
    public void action(Item item) {

    }
}
