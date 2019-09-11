package ru.kusoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import ru.kusoft.domain.Person;

@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final IOService io;
    private final MessageSource messageSource;

    public Person getPerson() {
        io.printlnLocal("hello.user");
        String secondName = io.inputString(
                messageSource.getMessage(
                        "user.secondname",
                        new String[] {},
                        io.getLocale()
                )
        );
        String firstName = io.inputString(
                messageSource.getMessage(
                        "user.firstname",
                        new String[] {},
                        io.getLocale()
                )
        );
        io.print("\n");
        return new Person(secondName, firstName);

    };
}
