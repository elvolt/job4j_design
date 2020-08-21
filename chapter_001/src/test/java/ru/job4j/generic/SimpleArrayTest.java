package ru.job4j.generic;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {

    @Test
    public void testAddAndGet() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("Petr");
        arr.add("Ivan");
        arr.add("Andrei");
        assertThat(arr.get(0), is("Petr"));
        assertThat(arr.get(1), is("Ivan"));
        assertThat(arr.get(2), is("Andrei"));
    }

    @Test
    public void testSet() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(14);
        arr.add(15);
        arr.add(16);
        arr.set(1, 22);
        assertThat(arr.get(0), is(14));
        assertThat(arr.get(1), is(22));
        assertThat(arr.get(2), is(16));
    }

    @Test
    public void testRemove() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("Petr");
        arr.add("Ivan");
        arr.add("Andrei");
        arr.remove(0);
        assertThat(arr.get(0), is("Ivan"));
        assertThat(arr.get(1), is("Andrei"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidateIndex() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("Petr");
        arr.add("Ivan");
        arr.add("Andrei");
        arr.remove(4);
    }

    @Test
    public void testIterator() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        arr.add("Petr");
        arr.add("Ivan");
        arr.add("Andrei");
        Iterator<String> it = arr.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Petr"));
        assertThat(it.next(), is("Ivan"));
        assertThat(it.next(), is("Andrei"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorWhenNextFromEmpty() {
        SimpleArray<String> arr = new SimpleArray<>(5);
        Iterator<String> it = arr.iterator();
        it.next();
    }
}