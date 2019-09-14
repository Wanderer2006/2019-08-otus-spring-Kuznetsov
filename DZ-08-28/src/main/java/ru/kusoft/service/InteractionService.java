package ru.kusoft.service;

import ru.kusoft.domain.Question;

public interface InteractionService {

    void drawQuestion(Question question);

    int inputNumberAnswer(int countAnswer);
}
