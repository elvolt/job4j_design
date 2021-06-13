package ru.job4j.isp.ex2;

/*
    Нарушение ISP - не все телефоны могут фотографировать и имеют доступ в интренет
 */

public interface Phone {
    void makeCall();

    void makePhoto();

    void launchBrowser();
}
