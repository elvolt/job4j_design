package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Test
    public void whenWriteAndRead() {
        String source = "./data/server.log";
        String target = "./data/resultForAnalizy.log";
        Analizy analizy = new Analizy();
        List<String> expected = new ArrayList<>();
        expected.add("10:57:01;10:59:01");
        expected.add("11:01:02;11:02:02");
        List<String> result = new ArrayList<>();
        analizy.unavailable(source, target);
        try (BufferedReader in = new BufferedReader(
                new FileReader(target)
        )) {
            result = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(expected, is(result));
    }
}