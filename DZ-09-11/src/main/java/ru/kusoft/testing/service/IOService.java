package ru.kusoft.testing.service;

public interface IOService {

    void print(String formatStr, String... values);

    void println(String formatStr, String... values);

    void printLocale(String MessageKey, String... values);

    void printlnLocale(String MessageKey, String... values);

    String getBundleMessage(String messageKey);

    String getBundleMessage(String messageKey, Object[] objects);

    String inputString();

    String inputStringLocale(String promptMessageKey);

    int inputInt();

}
