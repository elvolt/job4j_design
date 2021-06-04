package ru.job4j.srp;

public interface Order {
    int getOrderNumber();

    String getOrderManager();

    String getManagersPhone();

    /*
        Нарушение принципа SRP, т.к. getManagersPhone -
        это ответсвенность класса Manager, а не класса Order
    */
}
