package ru.kusoft.testing.service;

import ru.kusoft.testing.domain.Question;

public interface InteractionService {

    void drawQuestion(Question question);

    int inputNumberAnswer(int countAnswer);
}
