package ru.job4j.io.puzzle;

import java.util.*;

public class Shell {
    private Deque<String> pathsStack = new ArrayDeque<>();

    private String normalizePath(String path) {
        return path.toLowerCase()
                // replace multiple "//" in path
                .replaceAll("/{2,}", "/")
                // remove last "/" if path contains it
                .replaceAll("\\b/$", "");
    }

    public Shell cd(String path) {
        String normalizedPath = normalizePath(path);
        if (normalizedPath.startsWith("/")) {
            String[] paths = normalizedPath.substring(1).split("/");
            Deque<String> newStack = new ArrayDeque<>();
            Arrays.stream(paths).forEach(newStack::push);
            pathsStack = newStack;
        } else {
            String[] paths = normalizedPath.split("/");
            for (String p : paths) {
                if (p.equals("..")) {
                    pathsStack.pop();
                    continue;
                }
                if (p.equals(".")) {
                    continue;
                }
                pathsStack.push(p);
            }
        }
        return this;
    }

    public String path() {
        if (pathsStack.size() == 0) {
            return "/";
        }
        List<String> paths = new ArrayList<>(pathsStack.size());
        Iterator<String> iterator = pathsStack.descendingIterator();
        while (iterator.hasNext()) {
            paths.add("/" + iterator.next());
        }
        return String.join("", paths);
    }
}
