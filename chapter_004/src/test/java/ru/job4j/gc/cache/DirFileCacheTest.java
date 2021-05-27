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
        String result1 = cache.load(file.getName());
        assertEquals(expected, result1);
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.println("Three");
        }
        String result2 = cache.load(file.getName());
        assertEquals(expected, result2);
        file.deleteOnExit();
    }
}