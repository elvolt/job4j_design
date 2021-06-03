package ru.job4j.srp;

import java.util.Map;

public interface TemplateGenerator {
    String produce(String template, Map<String, String> args);

    int getTemplateLength();
}
