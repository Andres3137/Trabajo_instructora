package com.empresa.crudmixto.controller;

import com.empresa.crudmixto.entity.Usuario;
import com.empresa.crudmixto.repository.UsuarioRepository;
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
public class UsuarioExportController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=usuarios.xlsx");

        List<Usuario> usuarios = usuarioRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Usuarios");

        org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Usuario");
        headerRow.createCell(2).setCellValue("Contraseña");

        int rowNum = 1;
        for (Usuario usuario : usuarios) {
            org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(usuario.getId());
            row.createCell(1).setCellValue(usuario.getUsername());
            row.createCell(2).setCellValue(usuario.getPassword());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @GetMapping("/usuarios/pdf")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=usuarios.pdf");

        List<Usuario> usuarios = usuarioRepository.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Lista de Usuarios"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
        table.addCell("ID");
        table.addCell("Usuario");
        table.addCell("Contraseña");

        for (Usuario usuario : usuarios) {
            table.addCell(String.valueOf(usuario.getId()));
            table.addCell(usuario.getUsername());
            table.addCell(usuario.getPassword());
        }

        document.add(table);
        document.close();
    }
}