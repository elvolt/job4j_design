package ru.job4j.collection;

import java.util.*;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();
        Map<Integer, String> prevMap = new HashMap<>();
        for (User u : previous) {
            prevMap.put(u.id, u.name);
        }
        Map<Integer, String> currentMap = new HashMap<>();
        for (User u : current) {
            currentMap.put(u.id, u.name);
        }
        Set<Integer> prevKeys = prevMap.keySet();
        Set<Integer> currentKeys = currentMap.keySet();
        Set<Integer> unionKeys = new HashSet<>(prevKeys);
        unionKeys.addAll(currentKeys);
        for (Integer key : unionKeys) {
            if (prevMap.get(key) == null) {
                result.added += 1;
                continue;
            }
            if (currentMap.get(key) == null) {
                result.deleted += 1;
                continue;
            }
            if (!currentMap.get(key).equals(prevMap.get(key))) {
                result.changed += 1;
            }
        }
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
