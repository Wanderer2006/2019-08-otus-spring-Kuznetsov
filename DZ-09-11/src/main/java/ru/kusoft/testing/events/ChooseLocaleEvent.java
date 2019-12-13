package ru.kusoft.testing.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.kusoft.testing.domain.Person;

public class ChooseLocaleEvent extends ApplicationEvent {

    public ChooseLocaleEvent(Object source) {
        super(source);
    }
}
