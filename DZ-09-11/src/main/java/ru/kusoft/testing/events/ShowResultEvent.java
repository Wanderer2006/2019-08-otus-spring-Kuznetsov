package ru.kusoft.testing.events;

import org.springframework.context.ApplicationEvent;

public class ShowResultEvent extends ApplicationEvent {

    public ShowResultEvent(Object source) {
        super(source);
    }
}
