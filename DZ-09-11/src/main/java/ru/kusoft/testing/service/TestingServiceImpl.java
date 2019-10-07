package ru.kusoft.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.config.ApplicationSettings;
import ru.kusoft.testing.dao.QuestionsDao;
import ru.kusoft.testing.domain.Question;
import ru.kusoft.testing.events.RunTestingEvent;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService, ApplicationListener<RunTestingEvent> {
    private final QuestionsDao questionsDao;
    private final PersonService personService;
    private final ApplicationSettings settings;

    @Override
    public void onApplicationEvent(RunTestingEvent runTestingEvent) {
        runTesting();
    }

    public void runTesting() {
        List<Question> questions = questionsDao.loadQuestion(settings.getLocaleDefault());
        personService.testingAndCalculateSumPoint(questions);
    }
}
