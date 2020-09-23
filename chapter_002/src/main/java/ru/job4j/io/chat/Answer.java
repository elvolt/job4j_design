package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Answer {
    private File answersSource;
    private List<String> answers;

    public Answer(File answersSource) {
        this.answersSource = answersSource;
        getAnswers();
    }

    private void getAnswers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(answersSource))) {
            answers = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateAnswer() {
        return answers.get((int) (Math.random() * answers.size()));
    }
}
