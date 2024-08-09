package com.dmdev.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

//    3 способ задания формата дат
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(Jsr310Converters.StringToLocalDateConverter.INSTANCE);
//    }
}
