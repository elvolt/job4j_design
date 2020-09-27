package ru.job4j.io.find;

import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    private Pattern convertMaskToRegExp(String str) {
        String patternString = str;
        patternString = patternString.replaceAll("\\*", "\\\\S*");
        patternString = patternString.replaceAll("\\?", "\\\\S?");
        patternString = patternString.replace(".", "\\.");
        return Pattern.compile("\\A" + patternString + "\\Z");
    }

    public List<Path> findByMask(String directory, String mask) throws IOException {
        Pattern pattern = convertMaskToRegExp(mask);
        SearchFiles searcher = new SearchFiles(
                path -> {
                    Matcher m = pattern.matcher(path.toFile().getName());
                    return m.find();
                }
        );
        Files.walkFileTree(Paths.get(directory), searcher);
        return searcher.getPaths();
    }

    public List<Path> findByFullName(String directory, String name) throws IOException {
        SearchFiles searcher = new SearchFiles(path -> path.toFile().getName().equals(name));
        Files.walkFileTree(Paths.get(directory), searcher);
        return searcher.getPaths();
    }

    public List<Path> findByRegExp(String directory, String regExp) throws IOException {
        Pattern pattern = Pattern.compile(regExp);
        SearchFiles searcher = new SearchFiles(
                path -> {
                    Matcher m = pattern.matcher(path.toFile().getName());
                    return m.find();
                }
        );
        Files.walkFileTree(Paths.get(directory), searcher);
        return searcher.getPaths();
    }

    public List<Path> find(String directory, String name, String mode) throws IOException {
        List<Path> result;
        switch (mode) {
            case "mask":
                result = findByMask(directory, name);
                break;
            case "full":
                result = findByFullName(directory, name);
                break;
            case "RegExp":
                result = findByRegExp(directory, name);
                break;
            default:
                result = new ArrayList<>();
        }
        return result;
    }

    public void writePathsToFile(List<Path> paths, String output) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(output)
                )
        )) {
            for (Path path : paths) {
                writer.write(path + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
