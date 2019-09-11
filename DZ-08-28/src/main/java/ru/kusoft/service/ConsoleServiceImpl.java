package ru.kusoft.service;

import ru.kusoft.domain.Answer;
import ru.kusoft.domain.Question;

import java.util.Objects;
import java.util.Scanner;

public class ConsoleServiceImpl implements ConsoleService {

    public void drawQuestion(Question question) {
        if (Objects.nonNull(question)) {
            System.out.println("Вопрос:\n" + question.getQuestion() + "\n");
            if (Objects.nonNull(question.getAnswers())) {
                System.out.println("Варианты ответов:");
                int index = 0;
                for (Answer answer: question.getAnswers()) {
                    System.out.println(++index + ". " + answer.getAnswer());
                }
                System.out.println();
                System.out.print("Ваш ответ[от 1 до " + question.getAnswers().size() + "]: ");
            }
        }
    }

    public int inputNumberAnswer(int countAnswer) {
        Scanner sc = new Scanner(System.in);
        boolean validValue = false;
        int number = 0;
        do {
            if (sc.hasNextInt()) {
                number = sc.nextInt();
                if (number > 0 && number <= countAnswer) {
                    validValue = true;
                } else {
                    System.out.print("Вы ввели неверный номер ответа. Повторите ввод[от 1 до " + countAnswer + "]: ");
                }
            }
        }
        while (!validValue);
        return number;
    }

    public String inputString(String prompt) {
        Scanner sc = new Scanner(System.in);
        String str = null;
        System.out.print(prompt + ": ");
        if (sc.hasNext()) {
            str = sc.next();
        }
        return str;
    }
}
