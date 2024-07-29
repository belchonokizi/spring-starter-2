package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.repository.CompanyRepository;
import com.dmdev.spring.integration.annotation.IT;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class CompanyRepositoryIT {
    private static final Integer APPLE_ID = 4;

    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        Optional<Company> google = companyRepository.findByCompanyName("google");
        assertTrue(google.isPresent());
        List<Company> aCompanies = companyRepository.findAllByCompanyNameContainingIgnoreCase("a");
        assertEquals(3, aCompanies.size());
    }

    @Test
    void delete() {
        Optional<Company> maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void findById() {
        Company company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertEquals(2, company.getLocales().size());
    }

}