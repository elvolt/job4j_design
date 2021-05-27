package ru.job4j.gc.cache;

public class Emulator {
    private DirFileCache cache;

    public void setDirectory(String dirPath) {
        this.cache = new DirFileCache(dirPath);
    }

    public void loadToCache(String filename) {
        cache.load(filename);
    }

    public String getFromCache(String filename) {
        return cache.get(filename);
    }
}
