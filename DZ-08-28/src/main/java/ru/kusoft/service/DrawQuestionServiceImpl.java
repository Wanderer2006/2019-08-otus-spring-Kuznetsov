package ru.kusoft.service;

import ru.kusoft.domain.Answer;
import ru.kusoft.domain.Question;

import java.util.Objects;

public class DrawQuestionServiceImpl implements DrawQuestionService {

    public void draw(Question question) {
        if (Objects.nonNull(question)) {
            System.out.println("Вопрос:\n" + question.getQuestion() + "\n");
            if (Objects.nonNull(question.getAnswers())) {
                System.out.println("Варианты ответов:");
                int index = 0;
                for (Answer answer: question.getAnswers()) {
                    System.out.println(++index + ". " + answer.getAnswer());
                }
            }
            System.out.println();
        }
    }
}
