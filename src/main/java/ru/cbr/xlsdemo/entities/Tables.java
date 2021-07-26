package ru.cbr.xlsdemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Таблица из схемы
 */
@Entity(name = "table")
@Table(name = "TABLES")
@Getter
@Setter
public class Tables {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String tableName;

    @ManyToOne(targetEntity = Schemas.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "schema_id")
    private Schemas schema;

}
