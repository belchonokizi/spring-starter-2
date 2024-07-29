package com.dmdev.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private LocalDate birthDate;

    private String firstname;

    private String lastname;

    //для перевода енама в строку
    @Enumerated(EnumType.STRING)
    private Role role;

    //много юзеров у 1 компании
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @ToString.Exclude
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<UserChat> userChats = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
