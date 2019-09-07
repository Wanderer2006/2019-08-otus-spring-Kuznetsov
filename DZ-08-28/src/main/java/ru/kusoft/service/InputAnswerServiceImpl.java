package ru.kusoft.service;

import ru.kusoft.domain.Answer;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InputAnswerServiceImpl implements InputAnswerService {

    public int getAnswerReturnPoint(List<Answer> answers) {
        if (Objects.isNull(answers)) return 0;

        Scanner sc = new Scanner(System.in);
        boolean validValue = false;
        int number = 0;
        do {
            System.out.print("Ваш ответ[от 1 до " + answers.size() + "]: ");
            if (sc.hasNextInt()) {
                number = sc.nextInt();
                if (number > 0 && number <= answers.size()) {
                    validValue = true;
                }
            }
        }
        while (!validValue);
        System.out.println("\n");
        return answers.get(number - 1).getPoint();
    }
}
