package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Pack {
    private static List<File> searchFiles(Path root, List<String> excludeExtensions)
            throws IOException {
        return Search.exclude(root, excludeExtensions)
                .stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        ArgZip keys = new ArgZip(args);
        Path root = Paths.get(keys.directory());
        try {
            zip.packFiles(
                    searchFiles(root, keys.exclude()), new File(keys.output()), root.toFile()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
