package com.example.finally2.util.file;

import com.example.finally2.execption.custom.FileExecption;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class UploadImage {

    public String upload(MultipartFile file) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileName = file.getOriginalFilename();

        if (file.isEmpty()) {
            throw new FileExecption("handleFileIsEmpty");
        } else if (fileName != null && !fileName.endsWith(".jpg") && !fileName.endsWith(".png")) {
           throw new RuntimeException("handleFileType");
        }
        File newFile = new File("D:/tt2/finally-2/img/" + fileName);

        try {
            inputStream = file.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            return fileName;
        } catch (Exception e) {
            System.out.println("execption : " + e.getMessage());
            throw new FileExecption("handleErrorFile");

        }
    }
}
