package ru.kusoft.service;

import lombok.RequiredArgsConstructor;
import ru.kusoft.dao.QuestionsDao;
import ru.kusoft.domain.Person;
import ru.kusoft.domain.Question;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {
    private final QuestionsDao questionsDao;
    private final InteractionService interactionService;
    private final ChooseLocaleService chooseLocaleService;
    private final IOService io;
    private final PersonService personService;
    private Locale locale;

    public void runTesting() {
        locale = chooseLocaleService.getLocale();
        io.setLocale(locale);
        Person person = personService.getPerson();
        List<Question> questions = questionsDao.loadQuestion(locale);
        int sumPoint = 0;
        for (Question question: questions) {
            interactionService.drawQuestion(question);
            if (question.getCountAnswers() > 0) {
                int numberAnswer = interactionService.inputNumberAnswer(question.getAnswers().size());
                sumPoint += question.getPointAnswer(numberAnswer);
            }
            io.println("\n");
        }
        io.printlnLocal("congratulation.user", person.getSecondName(), person.getFirstName(), String.valueOf(sumPoint));
    }
}
