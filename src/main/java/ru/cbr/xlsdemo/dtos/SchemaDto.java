package ru.cbr.xlsdemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class SchemaDto {
    private String name;
    private String table_name;
}