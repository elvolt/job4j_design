package ru.job4j.dip.ex3;

/*
    Есть система для отправки отчётов. Для отправки используется sender.
    Нарушение DI - зависимость от реализации EmailReportSender
 */

public class Reporter {
    public void sendReport() {
        new EmailReportSender().send();
    }
}
