package ru.kusoft.service;

import lombok.RequiredArgsConstructor;
import ru.kusoft.dao.QuestionsDao;
import ru.kusoft.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {
    private final QuestionsDao questionsDao;
    private final DrawQuestionService drawQuestionService;
    private final InputAnswerService inputAnswerService;

    public void runTesting() {
        List<Question> questions = questionsDao.loadQuestion();
        int summPoint = 0;
        for (Question question: questions) {
            drawQuestionService.draw(question);
            summPoint += inputAnswerService.getAnswerReturnPoint(question.getAnswers());
        }
        System.out.println("\nПоздравляем!!! Вы набрали " + summPoint + " баллов!");
    }
}
