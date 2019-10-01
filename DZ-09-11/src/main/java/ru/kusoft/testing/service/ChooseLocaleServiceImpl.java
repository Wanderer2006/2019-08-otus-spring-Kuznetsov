package ru.kusoft.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.config.ApplicationSettings;

@Service
@RequiredArgsConstructor
public class ChooseLocaleServiceImpl implements ChooseLocaleService {
    private final IOService ioService;
    private final ApplicationSettings settings;

    public void setDefaultLocale() {
        ioService.printlnLocale("choose.language");
        settings.getSupportedLanguagesList().forEach(lg -> ioService.println(lg));
        int number = 0;
        do {
            ioService.print("[1 - %s]: ", String.valueOf(settings.getSupportedLanguagesCount()));
            number = ioService.inputInt();
        } while (number < 1 || number > settings.getSupportedLanguagesCount());
        ioService.print("\n");
        settings.setLocaleDefaultByLanguage(settings.getSupportedLanguagesList().get(number - 1));
    }
}
