package ru.kusoft.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.domain.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final IOService ioService;

    public Person getPerson() {
        ioService.printlnLocale("hello.user");
        String secondName = ioService.inputStringLocale("user.secondname");
        String firstName = ioService.inputStringLocale("user.firstname");
        ioService.print("\n");
        return new Person(secondName, firstName);
    };

    public void congratulation(Person person, int sumPoint) {
        ioService.printlnLocale("congratulation.user", person.getSecondName(), person.getFirstName(), String.valueOf(sumPoint));
    }
}
