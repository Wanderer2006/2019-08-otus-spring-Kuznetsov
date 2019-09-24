package ru.kusoft.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.domain.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final IOService ioService;

    public Person getPerson() {
        ioService.printlnLocal("hello.user");
        String secondName = ioService.inputStringLocal("user.secondname");
        String firstName = ioService.inputStringLocal("user.firstname");
        ioService.print("\n");
        return new Person(secondName, firstName);
    };

    public void Congratulation(Person person, int sumPoint) {
        ioService.printlnLocal("congratulation.user", person.getSecondName(), person.getFirstName(), String.valueOf(sumPoint));
    }
}
