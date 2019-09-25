package ru.kusoft.testing.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.kusoft.testing.config.ApplicationSettings;
import ru.kusoft.testing.domain.Question;
import ru.kusoft.testing.exception.IllegalFormatCsvException;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Дао для загрузки данных из csv-файла должно")
class QuestionsDaoFromCSVTest {

    private ApplicationSettings applicationSettings;

    private QuestionsDao questionsDao;

    @BeforeEach
    void setUp() {
        applicationSettings = new ApplicationSettings();
    }

    @Test
    @DisplayName(" успешно загружать корректный csv-файл")
    void testLoadCorrectCsvQuestion() {
        applicationSettings.setFileResourceTemplate("/correct-questions-answers_%s.csv");
        questionsDao = new QuestionsDaoFromCSV(applicationSettings);
        List<Question> questions = questionsDao.loadQuestion(Locale.getDefault());
        assertThat(questions).isNotNull().hasSize(4);
    }

    @Test
    @DisplayName(" бросать исключение если для вопроса нет ответов")
    void shouldThrowIllegalFormatCsvExceptionIfNotAnswers() {
        applicationSettings.setFileResourceTemplate("/uncorrect-not-answers_%s.csv");
        questionsDao = new QuestionsDaoFromCSV(applicationSettings);
        assertThatThrownBy(() -> questionsDao.loadQuestion(Locale.getDefault())).isInstanceOf(IllegalFormatCsvException.class);
    }

    @Test
    @DisplayName(" бросать исключение если для ответа нет баллов")
    void shouldThrowIllegalFormatCsvExceptionIfNotPointForAnswer() {
        applicationSettings.setFileResourceTemplate("/uncorrect-not-point-for-answer_%s.csv");
        questionsDao = new QuestionsDaoFromCSV(applicationSettings);
        assertThatThrownBy(() -> questionsDao.loadQuestion(Locale.getDefault())).isInstanceOf(IllegalFormatCsvException.class);
    }
}