package ru.cbr.xlsdemo.exceptions;

import java.io.IOException;

/**
 * Ошибка, если не получится записать xls-файл
 */
public class WorkbookException extends IOException {
    public WorkbookException(IOException e) {
        super("Не смогли записать файл", e.getCause());
    }
}
