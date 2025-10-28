package com.empresa.crudmixto.controller;

import com.empresa.crudmixto.entity.proyecto;
import com.empresa.crudmixto.repository.ProyectoRepository;
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
public class ProyectoExportController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @GetMapping("/proyectos/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=proyectos.xlsx");

        List<proyecto> proyectos = proyectoRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Proyectos");

        org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Descripción");
        headerRow.createCell(3).setCellValue("Empleado ID");

        int rowNum = 1;
        for (proyecto proyecto : proyectos) {
            org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(proyecto.getId());
            row.createCell(1).setCellValue(proyecto.getNombre());
            row.createCell(2).setCellValue(proyecto.getDescripcion());
            row.createCell(3).setCellValue(proyecto.getEmpleadoId());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @GetMapping("/proyectos/pdf")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=proyectos.pdf");

        List<proyecto> proyectos = proyectoRepository.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Lista de Proyectos"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.addCell("ID");
        table.addCell("Nombre");
        table.addCell("Descripción");
        table.addCell("Empleado ID");

        for (proyecto proyecto : proyectos) {
            table.addCell(String.valueOf(proyecto.getId()));
            table.addCell(proyecto.getNombre());
            table.addCell(proyecto.getDescripcion());
            table.addCell(String.valueOf(proyecto.getEmpleadoId()));
        }

        document.add(table);
        document.close();
    }
}