package ru.kusoft.testing.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.kusoft.testing.domain.Person;

public class InitUserEvent extends ApplicationEvent {

    @Getter
    private Person person;

    public  InitUserEvent(Object source, Person person) {
        super(source);
        this.person = person;
    }
}
