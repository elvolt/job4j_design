package ru.job4j.collection;

import java.util.*;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();
        Map<Integer, String> map = new HashMap<>();
        for (User u : current) {
            map.put(u.id, u.name);
        }
        for (User u : previous) {
            if (map.get(u.id) == null) {
                result.deleted += 1;
                continue;
            }
            if (!map.get(u.id).equals(u.name)) {
                result.changed += 1;
            }
            map.remove(u.id);
        }
        result.added = map.size();
        return result;
    }

    public static class User {
        private int id;
        private String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
