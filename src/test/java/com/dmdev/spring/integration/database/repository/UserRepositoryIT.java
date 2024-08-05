package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.database.repository.UserRepository;
import com.dmdev.spring.dto.PersonalInfo2;
import com.dmdev.spring.dto.UserFilter;
import com.dmdev.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
class UserRepositoryIT {

    private final UserRepository userRepository;

    @Test
    @Commit
    void checkAuditing() {
        User user = userRepository.findById(2L).get();
        user.setBirthDate(user.getBirthDate().plusYears(1L));
        //чтобы накатить изменения
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkCustomImplementation() {
        UserFilter filter = new UserFilter(
                null, "ov", LocalDate.now()
        );
        List<User> users = userRepository.findAllByFilter(filter);
        assertThat(users).hasSize(3);
    }

    @Test
    void checkProjections() {
//        List<PersonalInfo> users = userRepository.findAllByCompanyId(1, PersonalInfo.class);
        List<PersonalInfo2> users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkPageable() {
        //пропускаем 1 страницу, выведется вторая с размером 2
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        Page<User> slice = userRepository.findAllBy(pageable);
        slice.forEach(user -> System.out.println(user.getCompany().getCompanyName()));
//        assertThat(result).hasSize(2);

        while(slice.hasNext()) {
            //в 1 итерации page поменяется на 2
           slice =  userRepository.findAllBy(slice.nextPageable());
           slice.forEach(user -> System.out.println(user.getCompany().getCompanyName()));
        }

    }

    @Test
    void checkSort() {
        Sort sort = Sort.sort(User.class).by(User::getId);
        List<User> sortedUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
        assertThat(sortedUsers).hasSize(3);
    }

    @Test
    void checkFirst() {
        Optional<User> top = userRepository.findTopByOrderByIdDesc();
        assertTrue(top.isPresent());
        top.ifPresent(user -> assertEquals(6, user.getId()));
    }

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(2);
    }

    @Test
    void checkUpdate() {
        User user = userRepository.getById(2L);
        assertEquals(Role.ADMIN, user.getRole());

        int resultCount = userRepository.updateRole(Role.USER, 2L, 5L);
        assertEquals(2, resultCount);

        /** при следующей проверке тест упадет, так как в PersistenceContext
         * - first level cache - сохранится старая неизмененная версия
         * нужно чистить PersistenceContext
         * после чистки старыми сущностями (user) пользоваться нельзя или
         * нужно заново положить в PersistenceContext
         **/
        User userSame = userRepository.getById(2L);
        assertEquals(Role.USER, userSame.getRole());
    }
}