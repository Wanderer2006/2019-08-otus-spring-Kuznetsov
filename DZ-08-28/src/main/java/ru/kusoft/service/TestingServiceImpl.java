package ru.kusoft.service;

import lombok.RequiredArgsConstructor;
import ru.kusoft.dao.QuestionsDao;
import ru.kusoft.domain.Person;
import ru.kusoft.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {
    private final QuestionsDao questionsDao;
    private final ConsoleService consoleService;

    public void runTesting() {
        System.out.println("Добрый день! Представьтесь пожалуйста.");
        Person person = new Person(consoleService.inputString("Фамилия"),
                consoleService.inputString("Имя"));
        List<Question> questions = questionsDao.loadQuestion();
        int sumPoint = 0;
        for (Question question: questions) {
            consoleService.drawQuestion(question);
            if (question.getCountAnswers() > 0) {
                int numberAnswer = consoleService.inputNumberAnswer(question.getAnswers().size());
                sumPoint += question.getPointAnswer(numberAnswer);
            }
            System.out.println("\n");
        }
        System.out.println("\nПоздравляем " + person.getSecondName() + " " + person.getFirstName() +
                "!!! Вы набрали " + sumPoint + " баллов!");
    }
}
