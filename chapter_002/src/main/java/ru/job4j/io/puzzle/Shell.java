package ru.job4j.io.puzzle;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Shell {
    private Path workingDirectory = Paths.get("/");

    public Shell cd(String path) {
        String normalizedPath = path.replaceAll("/{2,}", "");
        Path filepath = Paths.get(normalizedPath);
        if (filepath.isAbsolute()) {
            workingDirectory = filepath.normalize();
        } else {
            workingDirectory = workingDirectory.resolve(filepath).normalize();
        }
        return this;
    }

    public String path() {
        return workingDirectory.toString();
    }
}
