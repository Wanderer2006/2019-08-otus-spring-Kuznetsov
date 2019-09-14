package ru.kusoft.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.kusoft.dao.QuestionsDao;
import ru.kusoft.dao.QuestionsDaoFromCSV;

@PropertySource("classpath:application.property")
@Configuration
public class DaoConfig {

    @Bean
    public QuestionsDao questionsDao(@Value("${file.resource.template}") String fileResourceTemplate) {
        return new QuestionsDaoFromCSV(fileResourceTemplate);
    }
}
