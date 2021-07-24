package ru.cbr.xlsdemo.controllers;

import javassist.bytecode.ByteArray;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cbr.xlsdemo.services.ReportService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/metadata")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<byte[]> getReport(@RequestParam Long schemaId) {

        byte[] report = reportService.createReport(schemaId);

        String fileName = "Report_"+ LocalDate.now() + "_schema_" + schemaId + ".xls";

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(report);
    }
}