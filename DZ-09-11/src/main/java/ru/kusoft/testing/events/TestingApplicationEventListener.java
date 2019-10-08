package ru.kusoft.testing.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.kusoft.testing.service.ChooseLocaleService;
import ru.kusoft.testing.service.PersonService;
import ru.kusoft.testing.service.TestingService;

@Component
@RequiredArgsConstructor
public class TestingApplicationEventListener {
    private final PersonService personService;
    private final ChooseLocaleService chooseLocaleService;
    private final TestingService testingService;

    @EventListener
    public void onChooseLocaleEvent(ChooseLocaleEvent chooseLocaleEvent) {
        chooseLocaleService.setDefaultLocale();
    }

    @EventListener
    public void onInitUserEvent(InitUserEvent initUserEvent) {
        personService.getPerson().setFirstName(initUserEvent.getPerson().getFirstName());
        personService.getPerson().setSecondName(initUserEvent.getPerson().getSecondName());
    }

    @EventListener
    public void onRunTestingEvent(RunTestingEvent runTestingEvent) {
        testingService.runTesting();
    }

    @EventListener
    public void onShowResultEvent(ShowResultEvent showResultEvent) {
        personService.congratulation();
    }
}
