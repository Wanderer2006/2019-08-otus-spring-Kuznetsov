package ru.kusoft.testing.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.kusoft.testing.domain.Person;
import ru.kusoft.testing.events.ChooseLocaleEvent;
import ru.kusoft.testing.events.InitUserEvent;
import ru.kusoft.testing.events.RunTestingEvent;
import ru.kusoft.testing.events.ShowResultEvent;
import ru.kusoft.testing.service.IOService;

@ShellComponent
@RequiredArgsConstructor
public class TestingApplicationCommands {
    private boolean isLocaleSetting = false;
    private boolean isInitUser = false;
    private boolean isTestPassed = false;

    private final ApplicationEventPublisher publisher;
    private final IOService ioService;

    @ShellMethod(value = "Choose locale command", key = {"cl", "choose-locale"})
    public void chooseLocale() {
        this.isLocaleSetting = true;
        publisher.publishEvent(new ChooseLocaleEvent(this));
    }

    @ShellMethod(value = "Init user command (iu second-name и first-name)", key = {"iu", "init-user"})
    @ShellMethodAvailability(value = "isInitUserAvailable")
    public String initUser(@ShellOption(defaultValue = "not value") String secondName, @ShellOption(defaultValue = "not value") String firstName) {
        if ("not value".equals(secondName) || "not value".equals(firstName)) {
            return ioService.getBundleMessage("wrong.hello.user");
        }
        this.isInitUser = true;
        publisher.publishEvent(new InitUserEvent(this, new Person(secondName, firstName)));

        return ioService.getBundleMessage("welcome", new String[] {secondName, firstName});
    }

    @ShellMethod(value = "Run testing command ", key = {"rt", "run-testing"})
    @ShellMethodAvailability(value = "isRunTestingAvailable")
    public void runTesting() {
        this.isTestPassed = true;
        publisher.publishEvent(new RunTestingEvent(this));
    }

    @ShellMethod(value = "Show result command ", key = {"sr", "show-result"})
    @ShellMethodAvailability(value = "iShowResultAvailable")
    public void showResult() {
        publisher.publishEvent(new ShowResultEvent(this));
    }

    private Availability isInitUserAvailable() {
        return isLocaleSetting ? Availability.available() : Availability.unavailable(ioService.getBundleMessage("choose.language"));
    }

    private Availability isRunTestingAvailable() {
        return isInitUser ? Availability.available() : Availability.unavailable(ioService.getBundleMessage("you.are.not"));
    }

    private Availability iShowResultAvailable() {
        return isTestPassed ? Availability.available() : Availability.unavailable(ioService.getBundleMessage("take.test"));
    }

}
