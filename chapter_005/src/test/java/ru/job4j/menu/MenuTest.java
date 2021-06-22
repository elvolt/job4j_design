package ru.job4j.menu;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class MenuTest {
    @Test
    public void whenAddItem() {
        IMenu menu = new Menu();
        menu.addItem(new Item("Item 1"));
        menu.addItem(new Item("Item 2"));
        assertEquals(2, menu.getItems().size());
    }

    @Test
    public  void whenAddSubItem() {
        IMenu menu = new Menu();
        IItem item1 = new Item("Item 1");
        IItem item2 = new Item("Item 2");
        IItem item3 = new Item("Item 3");
        menu.addItem(item1);
        menu.addItem(item2);
        menu.addSubItem(item1, item3);
        assertEquals(item3, item1.getSubItems().get(0));
    }

    @Test
    public  void whenAddSubItems() {
        IMenu menu = new Menu();
        IItem item1 = new Item("Item 1");
        IItem item2 = new Item("Item 1.1");
        IItem item3 = new Item("Item 1.1.1");
        menu.addItem(item1);
        menu.addSubItem(item1, item2);
        menu.addSubItem(item2, item3);
        assertEquals(item3, item2.getSubItems().get(0));
    }

    @Test
    public void whenRemoveItem() {
        IMenu menu = new Menu();
        IItem item1 = new Item("Item 1");
        IItem item2 = new Item("Item 2");
        IItem item3 = new Item("Item 3");
        menu.addItem(item1);
        menu.addItem(item2);
        menu.addSubItem(item1, item3);
        menu.removeItem(item1);
        assertEquals(1, menu.getItems().size());
        assertEquals(Optional.empty(), menu.getItem(item1));
        assertEquals(0, item1.getSubItems().size());
    }
}