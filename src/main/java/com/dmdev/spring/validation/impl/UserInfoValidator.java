package com.dmdev.spring.validation.impl;

import com.dmdev.spring.dto.UserCreateEditDto;
import com.dmdev.spring.validation.UserInfo;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.util.StringUtils.hasText;

@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {
    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return hasText(value.getFirstname()) ||
               hasText(value.getLastname());
    }
}
