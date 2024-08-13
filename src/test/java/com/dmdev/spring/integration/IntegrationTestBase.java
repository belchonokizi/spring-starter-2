package com.dmdev.spring.integration;

import com.dmdev.spring.integration.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

//докер контейнер поднимается 1 для всех тестов
@IT
@Sql({
        "classpath:sql/data.sql"
})
//    2-ой способ установить пользователя в тест
@WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN, USER"})
public abstract class IntegrationTestBase {
    //указываем тег докер имеджа
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:14.1");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    //установка динамического урла
    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }
}
