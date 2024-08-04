package com.dmdev.spring.database.entity;

import lombok.*;
import javax.persistence.*;
import org.hibernate.Hibernate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//названия параметров здесь должны совпадать с параметрами метода
@NamedQuery(
        name = "Company.findByCompanyName",
        query = "select c from Company c where " +
                "lower(c.companyName) = lower(:companyName)"
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "company")
public class Company implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String companyName;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locales",
            joinColumns = @JoinColumn(name = "company_id"))
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> locales = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;
        return getId() != null && Objects.equals(getId(), company.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
