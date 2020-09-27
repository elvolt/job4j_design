package ru.job4j.io.find;

import java.nio.file.Path;
import java.util.List;

public class FinderMain {
    public static void main(String[] args) {
        Finder finder = new Finder();
        ArgsParser parser = new ArgsParser(args);
        List<Path> files = null;
        if (parser.isHelpActive()) {
            return;
        }
        try {
            files = finder.find(parser.getDirectory(), parser.getFileName(),
                    parser.getFindMode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (files != null && files.size() != 0) {
            finder.writePathsToFile(files, parser.getOutput());
        } else {
            System.out.println("Files not found");
        }
    }
}
