package ru.job4j.menu;

import org.junit.Test;

import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenAddMenuItemsWithSubItems() {
        Output out = new StubOutput();
        Item item1 = new SimpleItem("1. Task");
        Item subItem1 = new SimpleItem("1.1 Task");
        Item subItem2 = new SimpleItem("1.1.1 Task");
        Item subItem3 = new SimpleItem("1.2 Task");
        Item item2 = new SimpleItem("2. Task");
        Menu menu = new SimpleMenu();
        menu.addItem(item1);
        menu.addItem(item2);
        menu.addSubItem(item1, subItem1);
        menu.addSubItem(item1, subItem3);
        menu.addSubItem(subItem1, subItem2);
        MenuUI ui = new StartUI(menu, out);
        ui.showMenu();
        String expected = item1.getName() + System.lineSeparator()
                + "--" + subItem1.getName() + System.lineSeparator()
                + "----" + subItem2.getName() + System.lineSeparator()
                + "--" + subItem3.getName() + System.lineSeparator()
                + item2.getName();
        assertEquals(expected, out.toString());
    }
}