package ru.kusoft.testing.domain;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Question {
    private String question;
    private List<Answer> answers;

    public int getCountAnswers() {
        if (Objects.isNull(answers)) return 0;

        return answers.size();
    }

    public int getPointAnswer(int numberAnswer) {
        if (numberAnswer > 0 && numberAnswer <= getCountAnswers()) {
            return answers.get(numberAnswer - 1).getPoint();
        }
        return 0;
    }
}
