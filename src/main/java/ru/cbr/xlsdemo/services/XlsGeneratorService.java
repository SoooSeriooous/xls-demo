package ru.cbr.xlsdemo.services;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.cbr.xlsdemo.components.WorkbookComponent;
import ru.cbr.xlsdemo.dtos.SchemaDto;
import ru.cbr.xlsdemo.exceptions.WorkbookException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class XlsGeneratorService {

    private final WorkbookComponent workbookComponent;

    public XlsGeneratorService(WorkbookComponent workbookComponent) {
        this.workbookComponent = workbookComponent;
    }

    /**
     * Создаём отчет по данным из списка
     *
     * @param schemas список с данными
     * @return массив байтов с данными
     * @throws WorkbookException кидаем, если не получилось записать отчет
     */
    public byte[] createReport(List<SchemaDto> schemas) throws WorkbookException {
        XSSFWorkbook report = workbookComponent.createWorkBook();

        final String sheetName = "Schemas";
        List<String> columnNames = Collections.unmodifiableList(Arrays.asList("schema", "name"));

        workbookComponent.createSheets(report, sheetName, columnNames.size());
        workbookComponent.createHeaderRow(report, sheetName, columnNames);
        addValues(report, sheetName, schemas);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            report.write(baos);
            report.close();
        } catch (IOException e) {
            throw new WorkbookException(e);
        }
        return baos.toByteArray();
    }

    /**
     * Заполняем отчет данными из списка
     *
     * @param report    отчет
     * @param sheetName имя страницы
     * @param schemas   список с данными
     */
    private void addValues(XSSFWorkbook report, String sheetName, List<SchemaDto> schemas) {
        CellStyle style = report.createCellStyle();
        style.setWrapText(true);
        final XSSFSheet sheet = report.getSheet(sheetName);

        int rowIterator = 1;
        for (SchemaDto dto : schemas) {
            XSSFRow row = sheet.createRow(rowIterator++);

            //Заполнили данные по схеме
            Cell cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(dto.getSchemaName());

            //Заполнили данные по таблицам
            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue(dto.getTableName());
        }
    }


}
