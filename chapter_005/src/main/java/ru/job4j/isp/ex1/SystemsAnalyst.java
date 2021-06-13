package ru.job4j.isp.ex1;

public class SystemsAnalyst implements ItSpecialist {
    private String name;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void writeCode() {
        throw new UnsupportedOperationException("Analyst don't write code");
    }
}
