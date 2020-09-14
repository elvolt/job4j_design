package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> notAvailableStatuses = new ArrayList<>(2);
        notAvailableStatuses.add("400");
        notAvailableStatuses.add("500");
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     )
             )
        ) {
            StringBuilder times = new StringBuilder();
            boolean available = true;
            String line;
            while ((line = in.readLine()) != null) {
                String[] typeDatePair = line.split(" ");
                if (available && notAvailableStatuses.contains(typeDatePair[0])) {
                    times.append(typeDatePair[1]).append(";");
                    available = false;
                } else if (!available && !notAvailableStatuses.contains(typeDatePair[0])) {
                    times.append(typeDatePair[1]).append(System.lineSeparator());
                    available = true;
                }
            }
            out.write(String.valueOf(times));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new FileOutputStream("chapter_002/unavailable.csv")
        )) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
