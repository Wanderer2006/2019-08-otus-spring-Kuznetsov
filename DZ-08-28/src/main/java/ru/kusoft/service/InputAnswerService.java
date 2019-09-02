package ru.kusoft.service;

import ru.kusoft.domain.Answer;

import java.util.List;

public interface InputAnswerService {

    int getAnswerReturnPoint(List<Answer> answers);
}
