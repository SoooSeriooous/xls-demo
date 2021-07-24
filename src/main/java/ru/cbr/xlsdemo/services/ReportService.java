package ru.cbr.xlsdemo.services;

import org.springframework.stereotype.Service;
import ru.cbr.xlsdemo.dtos.SchemaDto;
import ru.cbr.xlsdemo.entities.Schemas;
import ru.cbr.xlsdemo.entities.Tables;
import ru.cbr.xlsdemo.repositories.SchemasRepository;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ReportService {

    private final SchemasRepository schemasRepository;
    private final XlsGeneratorService xlsGeneratorService;

    public ReportService(SchemasRepository schemasRepository, XlsGeneratorService xlsGeneratorService) {
        this.schemasRepository = schemasRepository;
        this.xlsGeneratorService = xlsGeneratorService;
    }

    public byte[] createReport(Long schemaId) {
        final Optional<Schemas> optionalSchemas = schemasRepository.findById(schemaId);
        if (optionalSchemas.isPresent()){
            final Schemas schemas = optionalSchemas.get();
            List<SchemaDto> dtos = new ArrayList<>();
            for (Tables tables : schemas.getTables()) {
                dtos.add(new SchemaDto(schemas.getName(), tables.getTableName()));
            }
            xlsGeneratorService.createReport(dtos);
        }

        return new byte[] {};
    }
}
