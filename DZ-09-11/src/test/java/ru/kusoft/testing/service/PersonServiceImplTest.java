package ru.kusoft.testing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import ru.kusoft.testing.domain.Person;
import ru.kusoft.testing.events.InitUserEvent;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Метод сервиса PersonService должен ")
@SpringBootTest(classes = PersonServiceImpl.class)
class PersonServiceImplTest {

    @MockBean
    private IOService ioService;

    @MockBean
    private InteractionService interactionService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PersonService personService;

    @BeforeEach
    void setUp() {
        publisher.publishEvent(new InitUserEvent(this, new Person("Сидоров", "Иван")));
    }

    @Test
    @DisplayName("корректно инициализировать объект Person")
    void getPerson() {
        Person expectedPerson = new Person("Сидоров", "Иван");
        Person actualPerson = personService.getPerson();
        assertThat(actualPerson).usingRecursiveComparison()
                .isEqualTo(expectedPerson);
    }
}