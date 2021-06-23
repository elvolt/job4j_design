package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SimpleItem implements Item {
    private final String name;
    private final List<Item> subItems = new ArrayList<>();
    private Item parent;

    public SimpleItem(String name) {
        this.name = name;
    }

    public SimpleItem(String name, Item parent) {
        this(name);
        this.parent = parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Item> getSubItems() {
        return subItems;
    }

    @Override
    public boolean addSubItem(Item item) {
        return subItems.add(item);
    }

    @Override
    public boolean removeSubItem(Item item) {
        return subItems.remove(item);
    }

    @Override
    public Optional<Item> getParent() {
        return Optional.ofNullable(parent);
    }

    @Override
    public void setParent(Item parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleItem item = (SimpleItem) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Item{"
                + "name='" + name + '\''
                + '}';
    }
}
