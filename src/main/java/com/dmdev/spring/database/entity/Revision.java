package com.dmdev.spring.database.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
//должна быть единственной в проекте
@RevisionEntity
public class Revision {

    //эти 2 поля обязательны
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private Integer id;

    @RevisionTimestamp
    private Long timestamp;
}
