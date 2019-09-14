package ru.kusoft.dao;

import ru.kusoft.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionsDao {

    List<Question> loadQuestion(Locale locale);
}
