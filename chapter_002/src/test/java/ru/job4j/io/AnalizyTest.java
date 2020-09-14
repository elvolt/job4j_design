package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenWriteAndReadWithTemporaryFolder() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "200 10:59:01\n"
                    + "500 11:01:02\n"
                    + "200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> expected = new ArrayList<>();
        expected.add("10:57:01;10:59:01");
        expected.add("11:01:02;11:02:02");
        List<String> result = new ArrayList<>();
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