package ru.kusoft.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.config.ApplicationSettings;
import ru.kusoft.testing.events.ChooseLocaleEvent;

@Service
@RequiredArgsConstructor
public class ChooseLocaleServiceImpl implements ChooseLocaleService, ApplicationListener<ChooseLocaleEvent> {
    private final IOService ioService;
    private final ApplicationSettings settings;

    @Override
    public void onApplicationEvent(ChooseLocaleEvent chooseLocaleEvent) {
        setDefaultLocale();
    }

    public void setDefaultLocale() {
        ioService.printlnLocale("choose.language");
        settings.getSupportedLanguagesList().forEach(lg -> ioService.println(lg));
        int number = 0;
        do {
            ioService.print("[1 - %s]: ", String.valueOf(settings.getSupportedLanguagesCount()));
            number = ioService.inputInt();
        } while (number < 1 || number > settings.getSupportedLanguagesCount());
        settings.setLocaleDefaultByLanguage(settings.getSupportedLanguagesList().get(number - 1));
    }
}
