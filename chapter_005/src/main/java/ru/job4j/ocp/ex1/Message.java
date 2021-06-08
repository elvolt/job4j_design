package ru.job4j.ocp.ex1;

    /*
        Есть некоторый класс Message, который содержит некоторое сообщение ДЛЯ ОТПРАВКИ.
        Изначально сщщбщения отправлялись только на Консоль.
        Затем появилась необходимость в объектах класса Message, которые можно было бы
        записывать в файл. Т.к. поле out имеет тип конкретного объекта, а не абстракции,
        то это затрудняет расширение. Корректно было бы создать интерфейс Output,
        который реализовывался бы ConsoleOutput и был бы типом для поля out класса Message
     */

public class Message {
    private String text;
    private ConsoleOutput out;

    public Message() {
        out = new ConsoleOutput();
    }

    public void sendMessage() {
        out.send(text);
    }
}
