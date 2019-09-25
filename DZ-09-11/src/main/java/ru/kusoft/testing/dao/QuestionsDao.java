package ru.kusoft.testing.dao;

import ru.kusoft.testing.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionsDao {

    List<Question> loadQuestion(Locale locale);
}
