package ru.kusoft.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.config.ApplicationSettings;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

@Service
public class IOServiceImpl implements IOService{
    private final ApplicationSettings settings;
    private final MessageSource messageSource;

    private final InputStream is;
    private final PrintStream ps;
    private final Scanner sc;

    @Autowired
    public IOServiceImpl(ApplicationSettings settings, MessageSource messageSource) {
        this.settings = settings;
        this.is = settings.getInputStream();
        this.ps = settings.getPrintStream();
        this.messageSource = messageSource;
        this.sc = new Scanner(is);
    }

    public void print(String formatStr, String... values) {
        ps.printf(formatStr, values);
    }

    public void println(String formatStr, String... values) {
        print(formatStr, values);
        ps.println();
    }

    public void printLocal(String MessageKey, String... values) {
        List<String> valueList = new ArrayList<>();
        for (String value: values) {
            valueList.add(value);
        }

        ps.print(
                messageSource.getMessage(
                        MessageKey,
                        valueList.toArray(),
                        settings.getLocaleDefault()
                )
        );
    }

    public void printlnLocal(String MessageKey, String... values) {
        printLocal(MessageKey, values);
        ps.println();
    }

    public String inputString() {
        return sc.next();
    }

    public String inputStringLocal(String promptMessageKey) {
        printLocal(promptMessageKey);
        return sc.next();
    }

    public int inputInt() {
        int number = 0;
        if (sc.hasNextInt()) {
            number = sc.nextInt();
        }
        return number;
    }
}