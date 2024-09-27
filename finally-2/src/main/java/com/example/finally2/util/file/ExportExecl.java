package com.example.finally2.util.file;

import com.example.finally2.execption.custom.ListIsEmptyExecption;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@Component
public class ExportExecl {

    public <T> ByteArrayOutputStream exportToExcel(List<T> arrayList) throws IOException {
        if (arrayList == null || arrayList.isEmpty()) {
            throw new ListIsEmptyExecption("errorListExecl");
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Records");

        Row headerRow = sheet.createRow(0);
        Method[] methods = arrayList.get(0).getClass().getDeclaredMethods();
        int colNum = 0;

        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                headerRow.createCell(colNum++).setCellValue(method.getName().substring(3));
            }
        }

        int rowNum = 1;
        for (T record : arrayList) {
            Row row = sheet.createRow(rowNum++);
            colNum = 0;

            for (Method method : methods) {
                if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                    try {
                        Object value = method.invoke(record);
                        row.createCell(colNum++).setCellValue(value != null ? value.toString() : "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream;
    }

}
