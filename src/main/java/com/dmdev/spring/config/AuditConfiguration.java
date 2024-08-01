package com.dmdev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

//для включения аудита
@EnableJpaAuditing
@Configuration
public class AuditConfiguration {

    //для установки свойств createdBy и modifiedBy
    @Bean
    public AuditorAware<String> auditorAware() {
        // на реальной практике: SecurityContext.getCurrentUser().getEmail()
        //пока возвращаем определенное значение
        return () -> Optional.of("belchonok_izi");
    }
}
