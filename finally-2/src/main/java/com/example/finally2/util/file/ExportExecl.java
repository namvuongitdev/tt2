package com.example.finally2.util.file;

import com.example.finally2.entity.Category;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class ExportExecl {

    public void exportToExcel(String fileName, List<Object> objects) throws IOException, IllegalAccessException {
        // Kiểm tra xem file đã tồn tại hay chưa
        File file = new File(fileName);
        if (file.exists()) {
            file.delete(); // Xóa file nếu đã tồn tại
        }

        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Records");

        // Tạo tiêu đề cột
        Row headerRow = sheet.createRow(0);
        Field[] fields = objects.get(0).getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            headerRow.createCell(i).setCellValue(fields[i].getName());
        }

        // Xuất dữ liệu
        int rowNum = 1;
        for (Object record : objects) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(record);
                row.createCell(i).setCellValue(value != null ? value.toString() : "");
            }
        }

        // Ghi file vào hệ thống
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            workbook.write(fileOutputStream); // Ghi workbook vào file
            System.out.println("Export thành công: " + fileName); // Thông báo thành công
        } catch (IOException e) {
            e.printStackTrace();
            throw e; // Ném lại exception để xử lý
        } finally {
            workbook.close(); // Đảm bảo đóng workbook sau khi ghi
        }
    }

}
