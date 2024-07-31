package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.dto.PersonalInfo2;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, FilterUserRepository {

    //% -используются для containing
    //SimpleJpaQuery - более предпочтительный способ
    @Query("select u from User u " +
           "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    //NativeQuery чаще всего используется для проекций
    @Query(value = "SELECT * FROM users u WHERE u.username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

    //автоматическая очистка PersistenceContext до выполнения
    @Modifying(clearAutomatically = true)
    @Query("update User u " +
           "set u.role = :role " +
           "where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    //блокировки запросов на уровне бд, optimistic - на уровне приложения
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

//    Slice<User> findAllBy(Pageable pageable);

    /**
     * При работе с Page есть доп запрос на count, если нам нужно отражать количество страничек
     * Дефолтный запрос можно изменить в запросе @Query
     *
     * @EntityGraph("User.company") - избавляет от доп запросов на company,
     * что повышает производительность, но работа с именованными графами не предпочтительна
     **/
//    описываем, какие свойства надо подтягивать
    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Query(value = "select u from User u",
            countQuery = "select count(distinct u.firstname) from User u")
    //Page c @EntityGraph не будет корректно работать!
    Page<User> findAllBy(Pageable pageable);

    //    List<PersonalInfo> findAllByCompanyId(Integer companyId);
//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);
    @Query(value = "SELECT firstname, lastname, birth_date birthDate " +
                   "FROM users " +
                   "WHERE company_id = :companyId",
            nativeQuery = true)
    List<PersonalInfo2> findAllByCompanyId(Integer companyId);

}
