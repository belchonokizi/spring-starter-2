package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    //SimpleJpaQuery
    //join fetch возможен только с ним
    @Query("select c from Company c " +
//           "join fetch c.locales cl " +
           "where c.companyName = :companyName")
    Optional<Company> findByCompanyName(String companyName);

    //Collection, Stream(batch, close)
    //PartTreeJpaQuery
    List<Company> findAllByCompanyNameContainingIgnoreCase(String fragment);
}
