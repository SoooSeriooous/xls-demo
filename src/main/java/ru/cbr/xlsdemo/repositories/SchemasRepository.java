package ru.cbr.xlsdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cbr.xlsdemo.entities.Schemas;


@Repository
public interface SchemasRepository extends JpaRepository<Schemas, Long> {
}
