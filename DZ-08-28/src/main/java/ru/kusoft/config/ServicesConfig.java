package ru.kusoft.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kusoft.dao.QuestionsDao;
import ru.kusoft.service.*;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
public class ServicesConfig {
    private static final InputStream inputStream = System.in;
    private static final PrintStream printStream = System.out;

    @Bean
    public IOService ioService(MessageSource messageSource) {
        return new IOServiceImpl(inputStream, printStream, messageSource);
    }

    @Bean
    public ChooseLocaleService localeService(IOService io) {
        return new ChooseLocaleServiceImpl(io);
    }

    @Bean
    public PersonService personService(IOService io, MessageSource messageSource) {
        return new PersonServiceImpl(io, messageSource);
    }

    @Bean
    public InteractionService consoleService(IOService io) {
        return new InteractionServiceImpl(io);
    }

    @Bean
    public TestingService testingService(QuestionsDao questionsDao, InteractionService interactionService, ChooseLocaleService chooseLocaleService,
                                         IOService io, PersonService personService) {
        return new TestingServiceImpl(questionsDao, interactionService, chooseLocaleService, io, personService);
    }
}
