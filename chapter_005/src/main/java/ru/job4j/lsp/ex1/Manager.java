package ru.job4j.lsp.ex1;

/*
    Видим нарушение принципа LSP, т.к. усилено предусловие
    по возрасту для менеджеров
 */

public class Manager extends Employee {
    @Override
    public void setAge(int age) {
        if (age < 21) {
            throw new IllegalArgumentException("Too young for manager");
        }
        super.setAge(age);
    }
}
