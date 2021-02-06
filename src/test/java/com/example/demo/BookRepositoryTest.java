package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers // automatically starts and stops all defined containers
@ContextConfiguration(initializers = {BookRepositoryTest.Initializer.class}) // set datasource properties from container
public class BookRepositoryTest {

    @Container
    private static final DemoPostgreSQLContainer postgreSQLContainer = DemoPostgreSQLContainer.getInstance();

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(applicationContext.getEnvironment());
        }
    }

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    public void shouldInsertBook() {
        // automatically performs flyway migrations

        Book book = new Book();
        book.setName("Es");

        Book storedBook = bookRepository.save(book);

        assertThat(storedBook).isNotNull();
    }
}
