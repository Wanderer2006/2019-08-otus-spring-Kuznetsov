package ru.kusoft.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kusoft.testing.domain.Answer;
import ru.kusoft.testing.domain.Question;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InteractionServiceImpl implements InteractionService {
    private final IOService ioService;

    public void drawQuestion(Question question) {
        if (Objects.nonNull(question)) {
            ioService.printlnLocale("question", question.getQuestion());
            if (Objects.nonNull(question.getAnswers())) {
                ioService.printlnLocale("answers.variant");
                int index = 0;
                for (Answer answer: question.getAnswers()) {
                    ioService.printlnLocale("answer", String.valueOf(++index), answer.getAnswer());
                }
                ioService.printLocale("answers.numbers", String.valueOf(question.getAnswers().size()));
            }
        }
    }

    public int inputNumberAnswer(int countAnswer) {
        boolean validValue = false;
        int number = 0;
        do {
            number = ioService.inputInt();
            if (number > 0 && number <= countAnswer) {
                validValue = true;
            } else {
                ioService.printLocale("answer.wrong", String.valueOf(countAnswer));
            }
        }
        while (!validValue);
        ioService.println("\n");

        return number;
    }
}
