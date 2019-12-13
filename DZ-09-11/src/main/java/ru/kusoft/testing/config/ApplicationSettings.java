package ru.kusoft.testing.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Data
@ConfigurationProperties("application")
public class ApplicationSettings {
    private static final InputStream INPUT_STREAM = System.in;
    private static final PrintStream PRINT_STREAM = System.out;

    private String fileResourceTemplate;

    private String baseName;

    private String defaultEncoding;

    private Locale localeDefault;

    private HashMap<Locale, String> languages;

    public InputStream getInputStream() {
        return INPUT_STREAM;
    }

    public PrintStream getPrintStream() {
        return PRINT_STREAM;
    }

    public List<String> getSupportedLanguagesList() {
        return Collections.unmodifiableList(languages.values()
                .stream()
                .collect(Collectors.toList())
        );
    }

    public int getSupportedLanguagesCount() {
        return languages.size();
    }

    public Locale getLocaleByLanguage(String language) {
        return languages
                .entrySet()
                .stream()
                .filter(entry -> language.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public void setLocaleDefaultByLanguage(String language) {
        setLocaleDefault(getLocaleByLanguage(language));
    }
}
