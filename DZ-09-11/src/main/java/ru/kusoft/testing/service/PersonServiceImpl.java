package ru.kusoft.testing.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.domain.Person;
import ru.kusoft.testing.domain.Question;

import java.util.List;

@Service
@Data
public class PersonServiceImpl implements PersonService {
    private Person person;
    private Integer sumPoint;

    private final IOService ioService;
    private final InteractionService interactionService;

    @Autowired
    public PersonServiceImpl(IOService ioService, InteractionService interactionService) {
        this.ioService = ioService;
        this.interactionService = interactionService;
        person = new Person();
        sumPoint = 0;
    }

    public void updatePerson(Person person) {
        this.person.setFirstName(person.getFirstName());
        this.person.setSecondName(person.getSecondName());
    }

    public void testingAndCalculateSumPoint(List<Question> questions) {
        for (Question question: questions) {
            interactionService.drawQuestion(question);
            if (question.getCountAnswers() > 0) {
                int numberAnswer = interactionService.inputNumberAnswer(question.getAnswers().size());
                sumPoint += question.getPointAnswer(numberAnswer);
            }
        }
    }

    public void congratulation() {
        ioService.printlnLocale("congratulation.user", person.getSecondName(), person.getFirstName(), String.valueOf(sumPoint));
    }
}
