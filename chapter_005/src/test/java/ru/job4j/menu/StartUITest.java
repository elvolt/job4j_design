package ru.job4j.menu;

import org.junit.Test;

import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenAddMenuItemsWithSubItems() {
        Output out = new StubOutput();
        IItem item1 = new Item("1. Task");
        IItem subItem1 = new Item("1.1 Task");
        IItem subItem2 = new Item("1.1.1 Task");
        IItem subItem3 = new Item("1.2 Task");
        IItem item2 = new Item("2. Task");
        IMenu menu = new Menu();
        menu.addItem(item1);
        menu.addItem(item2);
        menu.addSubItem(item1, subItem1);
        menu.addSubItem(item1, subItem3);
        menu.addSubItem(subItem1, subItem2);
        IMenuUI ui = new StartUI(menu, out);
        ui.showMenu();
        String expected = item1.getName() + System.lineSeparator()
                + "--" + subItem1.getName() + System.lineSeparator()
                + "----" + subItem2.getName() + System.lineSeparator()
                + "--" + subItem3.getName() + System.lineSeparator()
                + item2.getName();
        assertEquals(expected, out.toString());
    }
}