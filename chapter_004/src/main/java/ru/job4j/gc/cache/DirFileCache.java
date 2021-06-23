package ru.job4j.gc.cache;

import java.io.*;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder result = new StringBuilder();
        File file = new File(cachingDir + File.separator + key);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not exists");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("File is not file");
        }
        try (BufferedReader in = new BufferedReader(
                new FileReader(file)
        )) {
            in.lines()
                    .forEach(result::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
