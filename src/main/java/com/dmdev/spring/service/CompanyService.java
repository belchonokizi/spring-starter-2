package com.dmdev.spring.service;

import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.repository.CrudRepository;
import com.dmdev.spring.dto.CompanyReadDTO;
import com.dmdev.spring.listener.entity.AccessType;
import com.dmdev.spring.listener.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    public Optional<CompanyReadDTO> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    publisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDTO(entity.id());
                });
    }
}
