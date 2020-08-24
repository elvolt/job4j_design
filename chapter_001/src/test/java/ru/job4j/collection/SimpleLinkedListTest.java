package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedListTest {

    @Test
    public void whenAddThenGet() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        list.add("second");
        String rsl = list.get(1);
        assertThat(rsl, is("second"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        list.add("second");
        Iterator<String> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.get(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        Iterator<String> it = list.iterator();
        list.add("second");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteLast() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.deleteLast();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteLastEmptyLinked() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.deleteLast();
    }

    @Test
    public void whenMultiDeleteLast() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteLast(), is(2));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
}