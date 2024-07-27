package com.dmdev.spring.service;

import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.repository.CrudRepository;
import com.dmdev.spring.dto.CompanyReadDTO;
import com.dmdev.spring.listener.entity.EntityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import javax.swing.text.html.Option;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//чтобы моки подтянулись
@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    private static final Integer COMPANY_ID = 1;

    @Mock
    private CrudRepository<Integer, Company> companyRepository;
    @Mock
    private UserService userService;
    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() {
        when(companyRepository.findById(COMPANY_ID))
                .thenReturn(Optional.of(new Company(COMPANY_ID, null, Collections.emptyMap())));

        Optional<CompanyReadDTO> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDTO expectedResult = new CompanyReadDTO(COMPANY_ID);

        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));

        //проверка, что моки были вызваны и отправился ивент
        verify(publisher).publishEvent(any(EntityEvent.class));
        //проверка, что не было больше взаимодействий с моками
        verifyNoMoreInteractions(publisher, userService);
    }
}