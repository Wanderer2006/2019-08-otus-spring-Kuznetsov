package ru.kusoft.testing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kusoft.testing.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Метод сервиса PersonService должен ")
@SpringBootTest(classes = PersonServiceImpl.class)
class PersonServiceImplTest {

    @MockBean
    private IOService ioService;

    @Autowired
    private PersonService personService;

    @BeforeEach
    void setUp() {
        given(ioService.inputStringLocale("user.secondname")).willReturn("Сидоров");
        given(ioService.inputStringLocale("user.firstname")).willReturn("Иван");
    }

    @Test
    @DisplayName("возвращать корректный объект Person")
    void getPerson() {
        Person expectedPerson = new Person("Сидоров", "Иван");
        Person actualPerson = personService.getPerson();
        assertThat(actualPerson).usingRecursiveComparison()
                .isEqualTo(expectedPerson);
    }
}