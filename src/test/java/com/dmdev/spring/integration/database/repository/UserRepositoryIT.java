package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.database.repository.UserRepository;
import com.dmdev.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryIT {

    private final UserRepository userRepository;

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");
        assertEquals(2, users.size());
    }
}