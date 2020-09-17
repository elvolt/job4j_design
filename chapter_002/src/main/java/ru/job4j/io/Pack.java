package ru.job4j.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Pack {

    public static void main(String[] args) {
        Zip zip = new Zip();
        ArgZip keys = new ArgZip(args);
        Path root = Paths.get(keys.directory());
        try {
            List<File> files = Search.exclude(root, keys.exclude())
                    .stream()
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            zip.packFiles(files, new File(keys.output()), root.toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
