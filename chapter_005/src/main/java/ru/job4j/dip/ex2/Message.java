package ru.job4j.dip.ex2;

    /*
        Нарушение DI - зависимость от реализации, а не от абстракции
        Нужен интерфейс для Output
     */

import ru.job4j.ocp.ex1.ConsoleOutput;

public class Message {
    private String text;
    private ru.job4j.ocp.ex1.ConsoleOutput out;

    public Message(ConsoleOutput out) {
        this.out = out;
    }

    public void sendMessage() {
        out.send(text);
    }
}