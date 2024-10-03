package com.example.finally2.util.file;

import com.example.finally2.execption.custom.FileExecption;
import org.apache.tika.Tika;
import org.apache.tika.mime.MimeTypes;
import org.apache.tika.mime.MimeTypesFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

@Component
public class UploadImage {

    private static final Tika tika = new Tika();
    private static final String urlRoot = "D:/tt2/finally-2/img/";

    private static boolean fileIsImage(MultipartFile file) {
        try {
            String mimiType = tika.detect(file.getInputStream());
            return mimiType.startsWith("image/");
        } catch (Exception e) {
            return false;
        }
    }

    public String upload(MultipartFile file) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileName = file.getOriginalFilename();

        if (file.isEmpty()) {
            throw new FileExecption("handleFileIsEmpty");
        }

        if (!fileIsImage(file)) {
            throw new FileExecption("handleFileType");
        }

        File newFile = new File(urlRoot + fileName);

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

    public static String converToBase64(String urlImage){
        try {
            if(urlImage != null){
                File file = new File(urlRoot + urlImage);
                byte[] bytes = Files.readAllBytes(file.toPath());
                return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bytes);
            }else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
