package ru.kusoft.testing.service;

public interface IOService {

    void print(String formatStr, String... values);

    void println(String formatStr, String... values);

    void printLocal(String MessageKey, String... values);

    void printlnLocal(String MessageKey, String... values);

    String inputString();

    String inputStringLocal(String promptMessageKey);

    int inputInt();

}
