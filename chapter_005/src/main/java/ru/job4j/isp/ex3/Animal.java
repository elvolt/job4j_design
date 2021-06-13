package ru.job4j.isp.ex3;

/*
    Нарушение ISP - не у всех животных есть хозяин и имя.
 */

public interface Animal {
    void getKind();

    void getOwner();

    void getName();
}
