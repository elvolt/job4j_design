package ru.job4j.ocp.ex2;

/*
    В данном примере есть классы домашних животных - Cat и Dog,
    а также класс Store, который соединяется с базой данных и возвращет нужный объект.
    В данном случае для каждого типа домашнего животного используется свой метод.
    При добавлении нового вида домашнего животного придётся добавлять ещё один метод в
    класс Store.
    Правильнее было бы классам Cat и Dog реализовывать интерфейс Pet, а методы заменить на
    один - getPetByName с возвращаемым значением типа Pet.
 */

public class Store {
    public Cat findCatByName(String name) {
        return null;
    }

    public Dog findDogByName(String name) {
       return null;
    }
}
