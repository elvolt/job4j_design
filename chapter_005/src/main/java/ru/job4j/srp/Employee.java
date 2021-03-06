package ru.job4j.srp;

import java.math.BigDecimal;

public interface Employee {
    void getName();

    void getSurname();

    void getRole();

    BigDecimal calculateSalary();

    /*
        Нарушение принципа SRP, т.к. calculateSalary -
        это ответсвенность отдела бухгалтерии
    */
}
