package ru.kusoft.service;

import org.springframework.context.MessageSource;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;


public class IOServiceImpl implements IOService{
    private final InputStream is;
    private final PrintStream ps;
    private final MessageSource messageSource;
    private Scanner sc;
    private Locale locale;

    public IOServiceImpl(InputStream is, PrintStream ps, MessageSource messageSource) {
        this.is = is;
        this.ps = ps;
        this.messageSource = messageSource;
        this.sc = new Scanner(is);
        this.locale = Locale.getDefault();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void print(String formatStr, Objects... values) {
        ps.printf(formatStr, values);
    }

    public void println(String formatStr, Objects... values) {
        print(formatStr, values);
        ps.println();
    }

    public void printLocal(String nameStr, String... values) {
        List<String> valueList = new ArrayList<>();
        for (String value: values) {
            valueList.add(value);
        }

        ps.print(
                messageSource.getMessage(
                        nameStr,
                        valueList.toArray(),
                        locale
                )
        );
    }

    public void printlnLocal(String nameStr, String... values) {
        printLocal(nameStr, values);
        ps.println();
    }

    public String inputString() {
        return sc.next();
    }

    public String inputString(String prompt) {
        ps.print(prompt);
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
