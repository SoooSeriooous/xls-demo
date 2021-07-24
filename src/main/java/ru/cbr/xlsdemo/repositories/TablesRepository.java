package ru.cbr.xlsdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cbr.xlsdemo.entities.Tables;

import java.util.UUID;

@Repository
public interface TablesRepository extends JpaRepository<Tables, Long> {
}
