package ru.cbr.xlsdemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



/**
 * Сущность для данных по схемам и таблицам
 */
@Getter
@Setter
@AllArgsConstructor
public class SchemaDto {
    private String schemaName;
    private String tableName;
}
