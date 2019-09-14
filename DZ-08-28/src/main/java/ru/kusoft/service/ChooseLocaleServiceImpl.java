package ru.kusoft.service;

import lombok.RequiredArgsConstructor;

import java.util.Locale;

@RequiredArgsConstructor
public class ChooseLocaleServiceImpl implements ChooseLocaleService {
    private final IOService io;

    public Locale getLocale() {
        io.println("Выберите язык (Choose language)\n1. Русский\n2. English");
        int number = 0;
        do {
            io.print("[1, 2]: ");
            number = io.inputInt();
        } while (number < 1 || number > 2);
        io.print("\n");
        return number == 1 ? Locale.getDefault() : Locale.ENGLISH;
    }
}
