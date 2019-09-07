package ru.kusoft.dao;

import ru.kusoft.domain.Answer;
import ru.kusoft.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionsDaoFromCSV implements QuestionsDao {
    private String fileResource;

    public void setFileResource(String fileResource) {
        this.fileResource = fileResource;
    }

    public List<Question> loadQuestion() {

        InputStream stream = getClass().getResourceAsStream(fileResource);
        List<String> result = new BufferedReader(new InputStreamReader(stream)).lines()
                .map(String::trim).collect(Collectors.toList());
        List<Question> questions = new ArrayList<>();
        for (String str: result) {
            Question question = new Question();
            question.setQuestion(str.substring(0, str.indexOf(";")));
            String answersStr = str.substring(str.indexOf(";") + 1);

            List<Answer> answers = new ArrayList<>();
            do {
                Answer answer = new Answer();
                if (answersStr.indexOf(";") > 0) {
                    answer.setAnswer(answersStr.substring(0, answersStr.indexOf(";")));
                    answersStr = answersStr.substring(answersStr.indexOf(";") + 1);
                } else {
                    answer.setAnswer(answersStr);
                    answersStr = "";
                }
                if (answersStr.indexOf(";") > 0) {
                    answer.setPoint(Integer.valueOf(answersStr.substring(0, answersStr.indexOf(";"))));
                    answersStr = answersStr.substring(answersStr.indexOf(";") + 1);
                } else {
                    if (answersStr.length() > 0) {
                        answer.setPoint(Integer.valueOf(answersStr));
                    } else {
                        answer.setPoint(0);
                    }
                    answersStr = "";
                }
                answers.add(answer);
            } while (answersStr.length() > 0);

            question.setAnswers(answers);
            questions.add(question);
        }

        return questions;
    }
}
