package com.dmdev.spring.config;

import com.dmdev.spring.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

//для включения аудита
@EnableJpaAuditing
//включает механизм и позволяет делать запросы к аудит таблицам
@EnableEnversRepositories(basePackageClasses = ApplicationRunner.class)
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
