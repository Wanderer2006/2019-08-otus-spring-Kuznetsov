package ru.kusoft.testing.service;

import ru.kusoft.testing.domain.Person;
import ru.kusoft.testing.domain.Question;

import java.util.List;

public interface PersonService {

    void updatePerson(Person person);
    void testingAndCalculateSumPoint(List<Question> questions);
    void congratulation();
    Person getPerson();
}
