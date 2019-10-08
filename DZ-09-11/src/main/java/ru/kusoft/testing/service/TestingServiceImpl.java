package ru.kusoft.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.config.ApplicationSettings;
import ru.kusoft.testing.dao.QuestionsDao;
import ru.kusoft.testing.domain.Question;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {
    private final QuestionsDao questionsDao;
    private final PersonService personService;
    private final ApplicationSettings settings;

    public void runTesting() {
        List<Question> questions = questionsDao.loadQuestion(settings.getLocaleDefault());
        personService.testingAndCalculateSumPoint(questions);
    }
}
