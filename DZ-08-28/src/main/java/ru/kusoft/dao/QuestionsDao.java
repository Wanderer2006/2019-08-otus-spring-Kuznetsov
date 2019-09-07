package ru.kusoft.dao;

import ru.kusoft.domain.Question;

import java.util.List;

public interface QuestionsDao {

    public List<Question> loadQuestion();
}
