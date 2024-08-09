package com.dmdev.spring.dto;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.validation.UserInfo;
import com.dmdev.spring.validation.group.CreateAction;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
//устанавливаем группу, для которой будет срабатывать аннотация
@UserInfo(groups = CreateAction.class)
public class UserCreateEditDto {
    @Email
    String username;

    //    2 способ задания формата, специфичного для этого поля
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    @Size(min = 3, max = 64)
    String firstname;

    String lastname;

    Role role;

    Integer companyId;

}
