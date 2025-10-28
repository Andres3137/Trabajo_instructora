package com.empresa.crudmixto.controller;

import com.empresa.crudmixto.entity.Empleado;
import com.empresa.crudmixto.repository.EmpleadoRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPTable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class EmpleadoExportController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=empleados.xlsx");

        List<Empleado> empleados = empleadoRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Empleados");

        org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Cargo");

        int rowNum = 1;
        for (Empleado empleado : empleados) {
            org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(empleado.getId());
            row.createCell(1).setCellValue(empleado.getNombre());
            row.createCell(2).setCellValue(empleado.getCargo());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @GetMapping("/empleados/pdf")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=empleados.pdf");

        List<Empleado> empleados = empleadoRepository.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Lista de Empleados"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
        table.addCell("ID");
        table.addCell("Nombre");
        table.addCell("Cargo");

        for (Empleado empleado : empleados) {
            table.addCell(String.valueOf(empleado.getId()));
            table.addCell(empleado.getNombre());
            table.addCell(empleado.getCargo());
        }

        document.add(table);
        document.close();
    }
}