package ru.kusoft.testing.service;

import ru.kusoft.testing.domain.Person;

public interface PersonService {

    Person getPerson();
    void Congratulation(Person person, int sumPoint);
}
