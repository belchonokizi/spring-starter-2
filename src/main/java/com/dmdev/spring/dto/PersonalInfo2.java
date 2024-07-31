package com.dmdev.spring.dto;

import org.springframework.beans.factory.annotation.Value;

//проекция для нативных запросов, нет полей, только методы
public interface PersonalInfo2 {

    String getFirstname();
    String getLastname();
    String getBirthDate();

    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();

}
