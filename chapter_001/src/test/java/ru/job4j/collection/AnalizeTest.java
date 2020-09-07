package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.List;

public class AnalizeTest {

    @Test
    public void whenAddUsers() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Petr");
        Analize.User user2 = new Analize.User(2, "Pavel");
        Analize.User user3 = new Analize.User(3, "Ivan");
        Analize.User user4 = new Analize.User(4, "Alex");
        List<Analize.User> previous = List.of(user1, user2);
        List<Analize.User> current = List.of(user1, user2, user3, user4);
        Analize.Info rsl = analize.diff(previous, current);
        assertThat(rsl.getAdded(), is(2));
    }

    @Test
    public void whenDeleteUsers() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Petr");
        Analize.User user2 = new Analize.User(2, "Pavel");
        Analize.User user3 = new Analize.User(3, "Ivan");
        Analize.User user4 = new Analize.User(4, "Alex");
        List<Analize.User> previous = List.of(user1, user2, user3, user4);
        List<Analize.User> current = List.of(user1, user3, user4);
        Analize.Info rsl = analize.diff(previous, current);
        assertThat(rsl.getDeleted(), is(1));
    }

    @Test
    public void whenChangeUsers() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Petr");
        Analize.User user2 = new Analize.User(2, "Pavel");
        Analize.User user3 = new Analize.User(3, "Ivan");
        Analize.User user4 = new Analize.User(4, "Alex");
        Analize.User user5 = new Analize.User(3, "Olga");
        List<Analize.User> previous = List.of(user1, user2, user3, user4);
        List<Analize.User> current = List.of(user1, user2, user5, user4);
        Analize.Info rsl = analize.diff(previous, current);
        assertThat(rsl.getChanged(), is(1));
    }

    @Test
    public void whenChangeDeleteAddUsers() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Petr");
        Analize.User user2 = new Analize.User(2, "Pavel");
        Analize.User user3 = new Analize.User(3, "Ivan");
        Analize.User user4 = new Analize.User(4, "Alex");
        Analize.User user5 = new Analize.User(5, "Olga");
        Analize.User user6 = new Analize.User(3, "Olga");
        List<Analize.User> previous = List.of(user1, user2, user3);
        List<Analize.User> current = List.of(user6, user4, user5);
        Analize.Info rsl = analize.diff(previous, current);
        assertThat(rsl.getAdded(), is(2));
        assertThat(rsl.getDeleted(), is(2));
        assertThat(rsl.getChanged(), is(1));
    }
}