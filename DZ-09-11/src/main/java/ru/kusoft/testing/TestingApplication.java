package ru.kusoft.testing;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.kusoft.testing.service.TestingService;
import ru.kusoft.testing.service.TestingServiceImpl;

@SpringBootApplication
@RequiredArgsConstructor
public class TestingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestingApplication.class, args);
		TestingService testingService = context.getBean(TestingService.class);
		testingService.runTesting();
	}
}
