package ru.job4j.menu;

import java.util.*;

public class Menu implements IMenu {
    private final List<IItem> items = new ArrayList<>();

    @Override
    public List<IItem> getItems() {
        return items;
    }

    @Override
    public void addItem(IItem item) {
        items.add(item);
    }

    @Override
    public boolean addSubItem(IItem parent, IItem child) {
        Optional<IItem> parentItem = getItem(parent);
        if (parentItem.isEmpty()) {
            return false;
        }
        parentItem.get().getSubItems().add(child);
        return true;
    }

    @Override
    public Optional<IItem> getItem(IItem item) {
        Optional<IItem> result = Optional.empty();
        Queue<IItem> data = new LinkedList<>();
        for (IItem i : items) {
            data.offer(i);
            while (!data.isEmpty()) {
                IItem head = data.poll();
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
    public boolean removeItem(IItem item) {
        Optional<IItem> removedItem = getItem(item);
        if (removedItem.isEmpty()) {
            return false;
        }
        removedItem.get().getSubItems().clear();
        return items.remove(item);
    }

    @Override
    public void action(IItem item) {
        System.out.println("Do action with " + item.getName());
    }
}
