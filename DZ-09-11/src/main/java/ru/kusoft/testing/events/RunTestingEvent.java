package ru.kusoft.testing.events;

import org.springframework.context.ApplicationEvent;

public class RunTestingEvent extends ApplicationEvent {

    public RunTestingEvent(Object source) {
        super(source);
    }
}
