package ru.cbr.xlsdemo.components;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkbookComponent {

    public XSSFWorkbook createWorkBook() {
        return new XSSFWorkbook();
    }

    public void createSheets(XSSFWorkbook workbook, String name, int columns) {
        final XSSFSheet sheet = workbook.createSheet(name);
        for (int i = 0; i < columns; i++) {
            sheet.setColumnWidth(i, 6000);
        }
    }

    public void createHeaderRow(XSSFWorkbook workbook, String sheetName) {
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
    }

    public void addColumnsNamesToHeader(XSSFWorkbook workbook, List<String> names) {

    }
}
