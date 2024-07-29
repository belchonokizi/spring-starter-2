package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //% -используются для containing
    //SimpleJpaQuery
    @Query("select u from User u " +
           "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    //NativeQuery чаще всего используется для проекций
    @Query(value = "SELECT * FROM users u WHERE u.username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);
}
