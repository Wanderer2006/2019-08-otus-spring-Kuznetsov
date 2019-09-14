package ru.kusoft.dao;

import lombok.RequiredArgsConstructor;
import ru.kusoft.domain.Answer;
import ru.kusoft.domain.Question;
import ru.kusoft.exception.IllegalFormatCsvException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QuestionsDaoFromCSV implements QuestionsDao {
    private final String fileResourceTemplate;

    public List<Question> loadQuestion(Locale locale) {

        String fileResource = String.format(fileResourceTemplate, locale.toString());
        InputStream stream = getClass().getResourceAsStream(fileResource);
        List<String> result = new BufferedReader(new InputStreamReader(stream)).lines()
                .map(String::trim).collect(Collectors.toList());

        return parseResult(result);
    }

    private List<Question> parseResult(List<String> result) {
        List<Question> questions = new ArrayList<>();
        for (String str: result) {
            if (str.length() == 0)
                throw new IllegalFormatCsvException("Отсутсвуют данные в строке файла");
            Question question = new Question();
            str = setQuestion(str, question);
            if (str.length() == 0)
                throw new IllegalFormatCsvException("Отсутствуют ответы для вопроса: " + question.getQuestion());
            question.setAnswers(getAnswers(str));
            questions.add(question);
        }

        return questions;
    }

    private String setQuestion(String str, Question question) {
        String answersStr = null;
        if (str.indexOf(";") > 0) {
            question.setQuestion(str.substring(0, str.indexOf(";")));
            answersStr = str.substring(str.indexOf(";") + 1);
        } else {
            question.setQuestion(str);
            answersStr = "";
        }

        return answersStr;
    }

    private List<Answer> getAnswers(String answersStr) {
        List<Answer> answers = new ArrayList<>();
        do {
            Answer answer = new Answer();
            answersStr = setAnswer(answersStr, answer);
            if (answersStr.length() == 0)
                throw new IllegalFormatCsvException("Отсутсвуют баллы для ответа: " + answer.getAnswer());
            answersStr = setPoint(answersStr, answer);
            answers.add(answer);
        } while (answersStr.length() > 0);

        return answers;
    }

    private String setAnswer(String str, Answer answer) {
        String answersStr;
        if (str.indexOf(";") > 0) {
            answer.setAnswer(str.substring(0, str.indexOf(";")));
            answersStr = str.substring(str.indexOf(";") + 1);
        } else {
            answer.setAnswer(str);
            answersStr = "";
        }

        return answersStr;
    }

    private String setPoint(String str, Answer answer) {
        String answersStr;
        if (str.indexOf(";") > 0) {
            answer.setPoint(Integer.valueOf(str.substring(0, str.indexOf(";"))));
            answersStr = str.substring(str.indexOf(";") + 1);
        } else {
            if (str.length() > 0) {
                answer.setPoint(Integer.valueOf(str));
            } else {
                answer.setPoint(0);
            }
            answersStr = "";
        }

        return answersStr;
    }
}
