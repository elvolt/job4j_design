package ru.job4j.menu;

import java.util.*;

public class SimpleMenu implements Menu {
    private final List<Item> items = new ArrayList<>();

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public boolean addSubItem(Item parent, Item child) {
        Optional<Item> parentItem = getItem(parent);
        if (parentItem.isEmpty()) {
            return false;
        }
        child.setParent(parent);
        return parentItem.get().addSubItem(child);
    }

    @Override
    public Optional<Item> getItem(Item item) {
        Optional<Item> result = Optional.empty();
        Queue<Item> data = new LinkedList<>();
        for (Item i : items) {
            data.offer(i);
            while (!data.isEmpty()) {
                Item head = data.poll();
                if (head.equals(item)) {
                    result = Optional.of(head);
                    break;
                }
                data.addAll(i.getSubItems());
            }
            if (result.isPresent()) {
                break;
            }
        }
        return result;
    }

    @Override
    public boolean removeItem(Item item) {
        Optional<Item> removedItem = getItem(item);
        if (removedItem.isEmpty()) {
            return false;
        }
        Optional<Item> parent = removedItem.get().getParent();
        if (parent.isEmpty()) {
            return items.remove(item);
        }
        return parent.get().removeSubItem(item);
    }

    @Override
    public void action(Item item) {
        System.out.println("Do action with " + item.getName());
    }
}