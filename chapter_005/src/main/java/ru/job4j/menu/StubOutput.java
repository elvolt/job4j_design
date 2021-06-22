package ru.job4j.menu;

public class StubOutput implements Output {
    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void print(Object obj) {
        if (obj != null) {
            buffer.append(obj.toString());
        } else {
            buffer.append("null");
        }
    }

    @Override
    public String toString() {
        return buffer.toString().trim();
    }
}
