package ru.kusoft.service;

import java.util.Locale;
import java.util.Objects;

public interface IOService {

    void setLocale(Locale locale);

    Locale getLocale();

    void print(String formatStr, Objects... values);

    void println(String formatStr, Objects... values);

    void printLocal(String nameStr, String... values);

    void printlnLocal(String nameStr, String... values);

    String inputString();

    String inputString(String prompt);

    int inputInt();

}
