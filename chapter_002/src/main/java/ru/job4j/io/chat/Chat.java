package ru.job4j.io.chat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chat {
    private static final String STOP_WORD = "Стоп";
    private static final String CONTINUE_WORD = "Продолжить";
    private static final String END_WORD = "Закончить";
    private File phrasesSource = new File(".\\chapter_002\\data\\phrases_source.txt");
    private File chatLog = new File(".\\chapter_002\\chat_log.txt");

    public Chat() {
    }

    public Chat(File phrasesSource, File chatLog) {
        this.phrasesSource = phrasesSource;
        this.chatLog = chatLog;
    }

    public void chat() {
        boolean botActive = true;
        List<String> log = new ArrayList<>();
        Answer answer = new Answer(phrasesSource);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в чат!");
        String userText;
        do {
            userText = scanner.nextLine();
            log.add("User: " + userText);
            if (userText.equals(STOP_WORD)) {
                botActive = false;
                continue;
            }
            if (userText.equals(CONTINUE_WORD)) {
                botActive = true;
            }
            if (botActive && !userText.equals(END_WORD)) {
                String currentAnswer = answer.generateAnswer();
                System.out.println(currentAnswer);
                log.add("Bot: " + currentAnswer);
            }
        } while (!userText.equals(END_WORD));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chatLog))) {
            for (String logItem : log) {
                writer.write(logItem + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
