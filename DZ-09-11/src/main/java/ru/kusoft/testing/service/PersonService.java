package ru.kusoft.testing.service;

import ru.kusoft.testing.domain.Person;

public interface PersonService {

    Person getPerson();
    void congratulation(Person person, int sumPoint);
}
