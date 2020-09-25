package ru.job4j.io.chat;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class Chat {
    private static final String STOP_WORD = "Стоп";
    private static final String RESUME_WORD = "Продолжить";
    private static final String FINISH_WORD = "Закончить";
    private File phrasesSource = new File(".\\chapter_002\\data\\phrases_source.txt");
    private File chatLog = new File(".\\chapter_002\\chat_log.txt");
    private boolean botActive = true;
    private boolean chatActive = true;
    private final Map<String, Consumer<Chat>> dispatch = new HashMap<>();

    public Chat() {
        initDispatch();
    }

    public Chat(File phrasesSource, File chatLog) {
        this();
        this.phrasesSource = phrasesSource;
        this.chatLog = chatLog;
    }

    private Consumer<Chat> stop() {
        return chat -> chat.botActive = false;
    }

    private Consumer<Chat> resume() {
        return chat -> chat.botActive = true;
    }

    private Consumer<Chat> finish() {
        return chat -> {
            chat.botActive = false;
            chat.chatActive = false;
        };
    }

    public void loadToDispatch(String word, Consumer<Chat> handle) {
        dispatch.put(word, handle);
    }

    private void initDispatch() {
        loadToDispatch(STOP_WORD, stop());
        loadToDispatch(RESUME_WORD, resume());
        loadToDispatch(FINISH_WORD, finish());
    }

    public void checkUserText(String userText) {
        if (dispatch.containsKey(userText)) {
            dispatch.get(userText).accept(this);
        }
    }

    public void chat() {
        List<String> log = new ArrayList<>();
        Answer answer = new Answer(phrasesSource);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в чат!");
        String userText;
        do {
            userText = scanner.nextLine();
            log.add("User: " + userText);
            checkUserText(userText);
            if (botActive) {
                String currentAnswer = answer.generateAnswer();
                System.out.println(currentAnswer);
                log.add("Bot: " + currentAnswer);
            }
        } while (chatActive);
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(chatLog)
                )
        )) {
            for (String logItem : log) {
                writer.write(logItem + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
