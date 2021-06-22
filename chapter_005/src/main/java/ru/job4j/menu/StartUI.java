package ru.job4j.menu;

import java.util.List;

public class StartUI implements IMenuUI {
    private static final String SEPARATOR = "--";
    private final IMenu menu;
    private final Output out;

    public StartUI(IMenu menu, Output out) {
        this.menu = menu;
        this.out = out;
    }

    private String printSubItems(int level, List<IItem> items) {
        StringBuilder result = new StringBuilder();
        items.forEach(item -> {
            int nestedLevel = level;
            while (nestedLevel != 0) {
                result.append(SEPARATOR);
                nestedLevel--;
            }
            result.append(item.getName())
                    .append(System.lineSeparator());
            if (item.getSubItems().size() != 0) {
                result.append(printSubItems(level + 1, item.getSubItems()));
            }
        });
        return result.toString();
    }

    @Override
    public void showMenu() {
        out.print(printSubItems(0, menu.getItems()).trim());
    }
}
