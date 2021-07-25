package ru.cbr.xlsdemo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.cbr.xlsdemo.dtos.SchemaDto;
import ru.cbr.xlsdemo.entities.Schemas;
import ru.cbr.xlsdemo.entities.Tables;
import ru.cbr.xlsdemo.exceptions.WorkbookException;
import ru.cbr.xlsdemo.repositories.SchemasRepository;

import javax.persistence.Table;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class ReportService {

    private final SchemasRepository schemasRepository;
    private final XlsGeneratorService xlsGeneratorService;

    public ReportService(SchemasRepository schemasRepository, XlsGeneratorService xlsGeneratorService) {
        this.schemasRepository = schemasRepository;
        this.xlsGeneratorService = xlsGeneratorService;
    }

    /**
     * Получаем данные для формирования отчета и отправляем их в сервис, формирующий файл выгрузки
     *
     * @param schemaId id схемы, по которой будет выгрузка
     * @return отчет, представленный в виде массива байтов
     */
    public Resource createReport(Long schemaId) {
        final Optional<Schemas> optionalSchemas = schemasRepository.findById(schemaId);
        byte[] bytes = new byte[] {};
        if (optionalSchemas.isPresent()) {
            final Schemas schemas = optionalSchemas.get();
            List<SchemaDto> dtos = new ArrayList<>();
            for (Tables tables : schemas.getTables()) {
                dtos.add(new SchemaDto(schemas.getName(), tables.getTableName()));
            }
            try {
                bytes = xlsGeneratorService.createReport(dtos);
            } catch (WorkbookException e) {
                log.info(e.getMessage());
            }
        }

        return new InputStreamResource(new ByteArrayInputStream(bytes));
    }
}
