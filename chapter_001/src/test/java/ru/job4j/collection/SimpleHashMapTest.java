package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMapTest {

    @Test
    public void whenInsertThenGet() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "a");
        String rsl = map.get(0);
        assertThat(rsl, is("a"));
    }

    @Test
    public void whenInsertThenResizeAndGet() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "a");
        map.insert(1, "b");
        map.insert(2, "c");
        map.insert(3, "d");
        map.insert(4, "e");
        map.insert(5, "f");
        map.insert(6, "g");
        map.insert(7, "h");
        map.insert(8, "i");
        map.insert(9, "j");
        map.insert(10, "k");
        map.insert(11, "l");
        map.insert(12, "m");
        map.insert(13, "n");
        map.insert(14, "o");
        map.insert(15, "p");
        String rsl = map.get(15);
        assertThat(rsl, is("p"));
    }

    @Test
    public void whenInsertThenIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "a");
        map.insert(1, "b");
        Iterator<String> it = map.iterator();
        assertThat(it.next(), is("a"));
        assertThat(it.next(), is("b"));
    }

    @Test
    public void whenInsertThenDelete() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "a");
        map.insert(1, "b");
        map.delete(0);
        Iterator<String> it = map.iterator();
        assertThat(it.next(), is("b"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(5, "a");
        map.get(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "a");
        Iterator<String> it = map.iterator();
        map.insert(1, "b");
        it.next();
    }
}