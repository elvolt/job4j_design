package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {
    @Ignore
    @Test
    public void whenAllArgsThenGetResult() {
        Generator generator = new GreetingGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Bill");
        args.put("subject", "you");
        String expected = "I am a Bill, Who are you?";
        assertEquals(expected, generator.produce(template, args));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenNotAllArgsThenThrowException() {
        Generator generator = new GreetingGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Bill");
        generator.produce(template, args);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenRedundantArgsThenThrowException() {
        Generator generator = new GreetingGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Bill");
        args.put("redundant", "value");
        args.put("subject", "you");
        generator.produce(template, args);
    }
}