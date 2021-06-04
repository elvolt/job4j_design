package ru.job4j.io.puzzle;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShellTest {

    @Test
    public void whenNewShell() {
        Shell shell = new Shell();
        assertEquals("/", shell.path());
    }

    @Test
    public void whenAbsolutePath() {
        Shell shell = new Shell();
        shell.cd("/path/to/file");
        shell.cd("/new/path/to/my/file");
        assertEquals("/new/path/to/my/file", shell.path());
    }

    @Test
    public void whenCdBack() {
        Shell shell = new Shell();
        shell.cd("/user");
        shell.cd("../root");
        assertEquals("/root", shell.path());
    }

    @Test
    public void whenCdRoot() {
        Shell shell = new Shell();
        shell.cd("/");
        assertEquals("/", shell.path());
    }

    @Test
    public  void whenCdBack2() {
        Shell shell = new Shell();
        shell.cd("usr/..");
        assertEquals("/", shell.path());
    }

    @Test
    public void whenCdRelativePath() {
        Shell shell = new Shell();
        shell.cd("usr");
        assertEquals("/usr", shell.path());
    }

    @Test
    public void whenCdChaining() {
        Shell shell = new Shell();
        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assertEquals("/usr/local", shell.path());
    }

    @Test
    public void whenCdChaining2() {
        Shell shell = new Shell();
        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        shell.cd("..");
        assertEquals("/usr", shell.path());
    }

    @Test
    public void whenCdWithDerangedPath() {
        Shell shell = new Shell();
        shell.cd("//lib///");
        assertEquals("/lib", shell.path());
    }
}