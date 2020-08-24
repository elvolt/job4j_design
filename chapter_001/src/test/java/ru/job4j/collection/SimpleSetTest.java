package ru.job4j.collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSetTest {
    @Test
    public void whenAddThenIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmpty() {
        SimpleSet<String> set = new SimpleSet<>();
        set.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        Iterator<String> it = set.iterator();
        set.add("second");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddDuplicateThenIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        set.add("second");
        set.add("first");
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
        it.next();
    }
}