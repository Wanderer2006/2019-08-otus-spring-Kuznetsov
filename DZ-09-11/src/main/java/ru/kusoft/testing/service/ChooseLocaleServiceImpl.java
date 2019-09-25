package ru.kusoft.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.config.ApplicationSettings;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChooseLocaleServiceImpl implements ChooseLocaleService {
    private final IOService ioService;
    private final ApplicationSettings settings;

    public void setDefaultLocale() {
        ioService.printlnLocal("choose.language");
        Map<Locale, String> languages = settings.getLanguages();
        languages.forEach((lc, lg) -> ioService.println(lg));
        int number = 0;
        do {
            ioService.print("[1, %s]: ", String.valueOf(languages.size()));
            number = ioService.inputInt();
        } while (number < 1 || number > languages.size());
        ioService.print("\n");
        settings.setLocaleDefault(new ArrayList<>(languages.keySet()).get(number - 1));
    }
}
