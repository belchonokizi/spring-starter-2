package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.dto.UserFilter;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter userFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);

        List<Predicate> predicates = new ArrayList<>();
        if (userFilter.firstname() != null) {
            predicates.add(criteriaBuilder.like(userRoot.get("firstname"),
                    userFilter.firstname()));
        }
        if (userFilter.lastname() != null) {
            predicates.add(criteriaBuilder.like(userRoot.get("lastname"),
                    userFilter.lastname()));
        }
        if (userFilter.birthDate() != null) {
            predicates.add(criteriaBuilder.lessThan(userRoot.get("birthDate"),
                    userFilter.birthDate()));
        }
        criteria.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria).getResultList();
    }
}
