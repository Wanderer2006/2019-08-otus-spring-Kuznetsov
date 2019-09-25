package ru.kusoft.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.config.ApplicationSettings;
import ru.kusoft.testing.dao.QuestionsDao;
import ru.kusoft.testing.domain.Person;
import ru.kusoft.testing.domain.Question;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {
    private final QuestionsDao questionsDao;
    private final InteractionService interactionService;
    private final ChooseLocaleService chooseLocaleService;
    private final PersonService personService;
    private final ApplicationSettings settings;

    public void runTesting() {
        chooseLocaleService.setDefaultLocale();
        Person person = personService.getPerson();
        List<Question> questions = questionsDao.loadQuestion(settings.getLocaleDefault());
        int sumPoint = TestingAndReturnSumPoint(questions);
        personService.congratulation(person, sumPoint);
    }

    public int TestingAndReturnSumPoint(List<Question> questions) {
        int sumPoint = 0;
        for (Question question: questions) {
            interactionService.drawQuestion(question);
            if (question.getCountAnswers() > 0) {
                int numberAnswer = interactionService.inputNumberAnswer(question.getAnswers().size());
                sumPoint += question.getPointAnswer(numberAnswer);
            }
        }

        return sumPoint;
    }
}
