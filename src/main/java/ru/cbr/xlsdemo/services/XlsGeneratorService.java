package ru.cbr.xlsdemo.services;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.cbr.xlsdemo.components.WorkbookComponent;
import ru.cbr.xlsdemo.dtos.SchemaDto;

import java.util.List;

@Service
public class XlsGeneratorService {

    private final WorkbookComponent workbookComponent;

    public XlsGeneratorService(WorkbookComponent workbookComponent) {
        this.workbookComponent = workbookComponent;
    }


    public void createReport(List<SchemaDto> schemas) {
        XSSFWorkbook report = workbookComponent.createWorkBook();
        final String sheetName = "Schemas";
        workbookComponent.createSheets(report, sheetName, schemas.size());
        workbookComponent.createHeaderRow(report, sheetName);

    }


}
