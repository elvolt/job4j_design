package ru.job4j.io;

import java.io.*;
import java.util.*;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter(line -> {
                String[] items = line.split(" ");
                return items[items.length - 2].equals("404");
            }).forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        List<String> log = filter("chapter_002\\log.txt");
        System.out.println(log);
    }
}
