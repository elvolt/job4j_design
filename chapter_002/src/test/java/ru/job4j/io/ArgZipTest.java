package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArgZipTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenCorrectArguments() throws IOException {
        File dir = folder.newFolder("dir1");
        String[] args = {"-d", dir.getAbsolutePath(), "-e", ".txt .java", "-o", "output.zip"};
        ArgZip keys = new ArgZip(args);
        assertThat(keys.directory(), is(dir.getAbsolutePath()));
        assertThat(keys.output(), is("output.zip"));
        assertThat(keys.exclude(), is(List.of(new String[]{".txt", ".java"})));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidOutputArgument() throws IOException {
        File dir = folder.newFolder("dir1");
        String[] args = {"-d", dir.getAbsolutePath(), "-e", ".txt .java", "-o", "output.rar"};
        ArgZip keys = new ArgZip(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidDirectoryArgument() throws IOException {
        File file = folder.newFile("file");
        String[] args = {"-d", file.getAbsolutePath(), "-e", ".txt .java", "-o", "output.rar"};
        ArgZip keys = new ArgZip(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongArgumentsCount1() {
        String[] args = {"-e", ".txt .java", "-o", "output.rar"};
        ArgZip keys = new ArgZip(args);
    }
}