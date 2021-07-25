package ru.cbr.xlsdemo.components;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkbookComponent {

    public XSSFWorkbook createWorkBook() {
        return new XSSFWorkbook();
    }

    /**
     * Создаём workbook, задаём ширину колонок
     *
     * @param workbook наш воркбук
     * @param name     имя рабочей страницы
     * @param columns  количество колонок на странице
     */
    public void createSheets(XSSFWorkbook workbook, String name, int columns) {
        final XSSFSheet sheet = workbook.createSheet(name);
        for (int i = 0; i < columns; i++) {
            sheet.setColumnWidth(i, 6000);
        }
    }

    /**
     * Создаём заголовок
     *
     * @param workbook    воркбук, с которым работаем
     * @param sheetName   имя рабочей страницы
     * @param columnNames список с именами колонок
     */
    public void createHeaderRow(XSSFWorkbook workbook, String sheetName, List<String> columnNames) {
        final XSSFSheet sheet = workbook.getSheet(sheetName);
        final XSSFRow header = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        for (int i = 0; i < columnNames.size(); i++) {
            XSSFCell cell = header.createCell(i);
            cell.setCellValue(columnNames.get(i));
            cell.setCellStyle(headerStyle);
        }
    }
}
