package ru.job4j.isp.ex1;

/*
    Нарушение ISP - не все IT специалисты пишут код
 */

public interface ItSpecialist {
    String getName();

    void writeCode();
}
