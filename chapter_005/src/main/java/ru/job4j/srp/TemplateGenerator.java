package ru.job4j.srp;

import java.util.Map;

public interface TemplateGenerator {
    String produce(String template, Map<String, String> args);

    int getTemplateLength();

    /*
        Нарушение принципа SRP, т.к. ответственность генератора - генерировать.
        Операции с шаблоном лучше вынести в отдельный класс - Template.
    */
}
