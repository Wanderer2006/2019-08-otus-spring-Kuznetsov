package ru.kusoft.service;

import lombok.RequiredArgsConstructor;
import ru.kusoft.domain.Answer;
import ru.kusoft.domain.Question;

import java.util.Objects;

@RequiredArgsConstructor
public class InteractionServiceImpl implements InteractionService {
    private final IOService io;

    public void drawQuestion(Question question) {
        if (Objects.nonNull(question)) {
            io.printlnLocal("question", question.getQuestion());
            if (Objects.nonNull(question.getAnswers())) {
                io.printlnLocal("answers.variant");
                int index = 0;
                for (Answer answer: question.getAnswers()) {
                    io.printlnLocal("answer", String.valueOf(++index), answer.getAnswer());
                }
                io.printLocal("answers.numbers", String.valueOf(question.getAnswers().size()));
            }
        }
    }

    public int inputNumberAnswer(int countAnswer) {
        boolean validValue = false;
        int number = 0;
        do {
            number = io.inputInt();
            if (number > 0 && number <= countAnswer) {
                validValue = true;
            } else {
                io.printLocal("answer.wrong", String.valueOf(countAnswer));
            }
        }
        while (!validValue);
        return number;
    }
}
