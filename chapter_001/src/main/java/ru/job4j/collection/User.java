package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Ivan", 3, new GregorianCalendar(1974, Calendar.JANUARY, 15));
        User user2 = new User("Ivan", 3, new GregorianCalendar(1974, Calendar.JANUARY, 15));
        Set<User> map = new HashSet<>();
        map.add(user1);
        map.add(user2);
        System.out.println(map);
    }
}
