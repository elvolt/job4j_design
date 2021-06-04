package ru.job4j.io.puzzle;

public class Shell {
    private String workingDirectory = "/";

    private String normalize(String path) {
        return path
                // replace "./" in path
                .replaceAll("/\\./", "")
                // replace ".." in path
                .replaceAll("/\\w*/\\.\\.", "/")
                // replace multiple "//" in path
                .replaceAll("/{2,}", "/")
                // remove last "/" if path contains it
                .replaceAll("\\b/$", "");
    }

    public Shell cd(String path) {
        if (path.startsWith("/")) {
            workingDirectory = normalize(path);
        } else {
            workingDirectory = normalize(workingDirectory + "/" + path);
        }
        return this;
    }

    public String path() {
        return workingDirectory;
    }
}
