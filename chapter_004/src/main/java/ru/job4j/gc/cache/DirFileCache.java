package ru.job4j.gc.cache;

import java.io.*;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    private String getFileContent(String filename) {
        StringBuilder result = new StringBuilder();
        File file = new File(cachingDir + File.separator + filename);
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

    @Override
    protected String load(String key) {
        String result = get(key);
        if (result == null) {
            String value = getFileContent(key);
            put(key, value);
            result = get(key);
        }
        return result;
    }
}
