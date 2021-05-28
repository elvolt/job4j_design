package ru.job4j.gc.cache;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class DirFileCacheTest {

    @Test
    public void whenLoadFileThenGetFileContentFromCache() throws IOException {
        File file = File.createTempFile("cache", ".txt");
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.println("One");
            out.println("Two");
        }
        String dirPath = file.getParent();
        DirFileCache cache = new DirFileCache(dirPath);
        String expected = "OneTwo";
        String key = file.getName();
        cache.load(key);
        String result1 = cache.get(key);
        assertEquals(expected, result1);
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.print("Three");
        }
        String result2 = cache.get(key);
        assertEquals(expected, result2);
        cache.load(key);
        String result3 = cache.get(key);
        String expected2 = "Three";
        assertEquals(expected2, result3);
        file.deleteOnExit();
    }
}