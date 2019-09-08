package ru.kusoft.service;

import ru.kusoft.domain.Question;

public interface ConsoleService {

    void drawQuestion(Question question);

    int inputNumberAnswer(int countAnswer);

    String inputString(String prompt);
}
