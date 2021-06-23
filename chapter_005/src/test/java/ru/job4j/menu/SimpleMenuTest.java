package ru.job4j.menu;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class SimpleMenuTest {
    @Test
    public void whenAddItem() {
        Menu menu = new SimpleMenu();
        menu.addItem(new SimpleItem("Item 1"));
        menu.addItem(new SimpleItem("Item 2"));
        assertEquals(2, menu.getItems().size());
    }

    @Test
    public  void whenAddSubItem() {
        Menu menu = new SimpleMenu();
        Item item1 = new SimpleItem("Item 1");
        Item item2 = new SimpleItem("Item 2");
        Item item3 = new SimpleItem("Item 3");
        menu.addItem(item1);
        menu.addItem(item2);
        menu.addSubItem(item1, item3);
        assertEquals(item3, item1.getSubItems().get(0));
    }

    @Test
    public  void whenAddSubItems() {
        Menu menu = new SimpleMenu();
        Item item1 = new SimpleItem("Item 1");
        Item item2 = new SimpleItem("Item 1.1");
        Item item3 = new SimpleItem("Item 1.1.1");
        menu.addItem(item1);
        menu.addSubItem(item1, item2);
        menu.addSubItem(item2, item3);
        assertEquals(item3, item2.getSubItems().get(0));
    }

    @Test
    public void whenRemoveItem() {
        Menu menu = new SimpleMenu();
        Item item1 = new SimpleItem("Item 1");
        Item item2 = new SimpleItem("Item 2");
        Item item3 = new SimpleItem("Item 3");
        menu.addItem(item1);
        menu.addItem(item2);
        menu.addSubItem(item1, item3);
        menu.removeItem(item1);
        assertEquals(1, menu.getItems().size());
        assertEquals(Optional.empty(), menu.getItem(item1));
    }
}