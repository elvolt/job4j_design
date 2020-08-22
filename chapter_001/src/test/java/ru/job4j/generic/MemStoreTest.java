package ru.job4j.generic;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class MemStoreTest {
    @Test
    public void whenAddNewItemThenStoreHasSameItem() {
        MemStore<User> store = new MemStore<>();
        User user1 = new User("1");
        User user2 = new User("4");
        store.add(user1);
        store.add(user2);
        User result = store.findById("4");
        assertThat(result, is(user2));
    }

    @Test
    public void whenReplace() {
        MemStore<User> store = new MemStore<>();
        User user1 = new User("1");
        User user2 = new User("4");
        User user3 = new User("8");
        store.add(user1);
        store.add(user2);
        store.replace("4", user3);
        assertThat(store.findById("8"), is(user3));
        assertThat(store.findById("4"), is(nullValue()));
    }

    @Test
    public void whenDelete() {
        MemStore<Role> store = new MemStore<>();
        Role role1 = new Role("1");
        Role role2 = new Role("4");
        Role role3 = new Role("8");
        store.add(role1);
        store.add(role2);
        store.add(role3);
        store.delete("1");
        assertThat(store.findById("1"), is(nullValue()));
    }
}