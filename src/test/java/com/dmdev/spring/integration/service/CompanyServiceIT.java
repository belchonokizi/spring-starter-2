package com.dmdev.spring.integration.service;

import com.dmdev.spring.config.DatabaseProperties;
import com.dmdev.spring.dto.CompanyReadDTO;
import com.dmdev.spring.integration.annotation.IT;
import com.dmdev.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * для интеграции JUnit5 и спрингового TestContext
 * @ExtendWith(SpringExtension.class)
 * указываем, какой именно appContext используем
 * @ContextConfiguration(classes = ApplicationRunner.class,
 * для чтения yaml файлов
 * initializers = ConfigDataApplicationContextInitializer.class)
 * всё, что сверху, заменяется @SpringBootTest аннотацией
 **/
@IT
@RequiredArgsConstructor
//Чтобы работал DI через конструктор
//Это перенесено в конфиг файл spring.properties
//@TestConstructor(autowireMode = AutowireMode.ALL)
public class CompanyServiceIT {
    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void test() {

        Optional<CompanyReadDTO> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDTO expectedResult = new CompanyReadDTO(COMPANY_ID);

        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}
