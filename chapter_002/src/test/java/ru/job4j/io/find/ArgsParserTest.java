package ru.job4j.io.find;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArgsParserTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenCorrectArguments() throws IOException {
        File dir = folder.newFolder("dir1");
        String[] args = {"-d", dir.getAbsolutePath(), "-n", "*.txt", "-m", "-o", "log.txt"};
        ArgsParser parser = new ArgsParser(args);
        assertThat(parser.getDirectory(), is(dir.getAbsolutePath()));
        assertThat(parser.getFileName(), is("*.txt"));
        assertThat(parser.getFindMode(), is("mask"));
        assertThat(parser.getOutput(), is("log.txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFewArguments() throws IOException {
        String[] args = {"-d", "dir", "-n", "*.txt", "-o", "log.txt"};
        ArgsParser parser = new ArgsParser(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongDirectory() throws IOException {
        String[] args = {"-d", "dir", "-n", "*.txt", "m", "-o", "log.txt"};
        ArgsParser parser = new ArgsParser(args);
    }
}