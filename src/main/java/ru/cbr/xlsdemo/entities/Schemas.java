package ru.cbr.xlsdemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Схема БД
 */
@Entity(name = "schema")
@Table(name = "SCHEMAS")
@Getter
@Setter
public class Schemas {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "schema", orphanRemoval = true)
    private Set<Tables> tables;

}
