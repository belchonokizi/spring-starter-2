package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.database.repository.UserRepository;
import com.dmdev.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IT
@RequiredArgsConstructor
class UserRepositoryIT {

    private final UserRepository userRepository;

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");
        assertEquals(2, users.size());
    }

    @Test
    void checkUpdate() {
        User user = userRepository.getReferenceById(2L);
        assertEquals(Role.ADMIN, user.getRole());

        int resultCount = userRepository.updateRole(Role.USER, 2L, 5L);
        assertEquals(2, resultCount);

        /** при следующей проверке тест упадет, так как в PersistenceContext
         * - first level cache - сохранится старая неизмененная версия
         * нужно чистить PersistenceContext
         * после чистки старыми сущностями (user) пользоваться нельзя или
         * нужно заново положить в PersistenceContext
         **/
        User userSame = userRepository.getReferenceById(2L);
        assertEquals(Role.USER, userSame.getRole());
    }
}