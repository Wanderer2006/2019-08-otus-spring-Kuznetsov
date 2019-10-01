package ru.kusoft.testing.service;

public interface IOService {

    void print(String formatStr, String... values);

    void println(String formatStr, String... values);

    void printLocale(String MessageKey, String... values);

    void printlnLocale(String MessageKey, String... values);

    String inputString();

    String inputStringLocale(String promptMessageKey);

    int inputInt();

}
