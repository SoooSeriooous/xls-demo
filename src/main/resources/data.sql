DROP table if exists SCHEMAS;
DROP TABLE if exists TABLES;

create table schemas
(
    id number auto_increment,
    name varchar
);

comment on table schemas is 'таблица со схемами';

create unique index SCHEMAS_ID_UINDEX
    on schemas (id);

alter table schemas
    add constraint SCHEMAS_PK
        primary key (id);

create table tables
(
    id number auto_increment,
    schema_id number not null,
    name varchar,
    constraint TABLES_SCHEMAS_ID_FK
        foreign key (schema_id) references SCHEMAS
);

comment on table tables is 'таблица с таблицами';

create unique index TABLES_ID_UINDEX
    on tables (id);

alter table tables
    add constraint TABLES_PK
        primary key (id);

INSERT INTO SCHEMAS (name) VALUES ( 'schema_1' );
INSERT INTO SCHEMAS (name) VALUES ( 'schema_2' );
INSERT INTO SCHEMAS (name) VALUES ( 'schema_3' );

INSERT INTO TABLES (schema_id, name) VALUES ( 1, 'table_1' );
INSERT INTO TABLES (schema_id, name) VALUES ( 1, 'table_2' );
INSERT INTO TABLES (schema_id, name) VALUES ( 2, 'table_3' );
INSERT INTO TABLES (schema_id, name) VALUES ( 2, 'table_4' );
INSERT INTO TABLES (schema_id, name) VALUES ( 3, 'table_5' );
INSERT INTO TABLES (schema_id, name) VALUES ( 3, 'table_6' );
INSERT INTO TABLES (schema_id, name) VALUES ( 3, 'table_7' );
