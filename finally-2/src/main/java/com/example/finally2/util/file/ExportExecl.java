package com.example.finally2.util.file;

import com.example.finally2.dto.categorydto.response.CategoryResponseExecl;
import com.example.finally2.dto.productdto.response.ProductResponseExecl;
import com.example.finally2.util.date.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExportExecl {


    public  ByteArrayOutputStream exportToExcelProduct(List<ProductResponseExecl> arrayList , List<String> title) throws IOException {
        Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("List Product");

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < title.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(title.get(i));

            CellStyle headerCellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (ProductResponseExecl productResponseExecl : arrayList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(productResponseExecl.getProductCode());
            row.createCell(1).setCellValue(productResponseExecl.getProductName());
            row.createCell(2).setCellValue(productResponseExecl.getPrice());
            row.createCell(3).setCellValue(productResponseExecl.getDescription());
            row.createCell(4).setCellValue(productResponseExecl.getQuantity());
            row.createCell(5).setCellValue(productResponseExecl.getImageNotBase());
            row.createCell(6).setCellValue(DateUtil.formatLocaleDate(productResponseExecl.getCreateAt()));
            row.createCell(7).setCellValue(DateUtil.formatLocaleDate(productResponseExecl.getModifiedDate()));
            row.createCell(8).setCellValue(productResponseExecl.getModifiedBy());
            row.createCell(9).setCellValue(productResponseExecl.getCreateBy());
            row.createCell(10).setCellValue(productResponseExecl.getStatus().toString());
            row.createCell(11).setCellValue(productResponseExecl.getCategorys());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream;
    }


    public  ByteArrayOutputStream exportToExcelCategory(List<CategoryResponseExecl> arrayList , List<String> title) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("List Category");

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < title.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(title.get(i));

            CellStyle headerCellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (CategoryResponseExecl categoryResponseExecl : arrayList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(categoryResponseExecl.getCategoryCode());
            row.createCell(1).setCellValue(categoryResponseExecl.getCategoryName());
            row.createCell(2).setCellValue(categoryResponseExecl.getDescription());
            row.createCell(3).setCellValue(DateUtil.formatLocaleDate(categoryResponseExecl.getCreateAt()));
            row.createCell(4).setCellValue(DateUtil.formatLocaleDate(categoryResponseExecl.getModifiedDate()));
            row.createCell(5).setCellValue(categoryResponseExecl.getModifiedBy());
            row.createCell(6).setCellValue(categoryResponseExecl.getCreateBy());
            row.createCell(7).setCellValue(categoryResponseExecl.getImage());
            row.createCell(8).setCellValue(categoryResponseExecl.getStatus().toString());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream;
    }


}
