package com.example.finally2.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static LocalDate getCurrentDate() {
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        return now;
    }

    public static String convertDateFormat(String date) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            d = sdf.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        sdf.applyPattern("dd/MM/yyyy");
        return sdf.format(d);
    }

    public static String formatLocaleDate(LocalDate localDate){
        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = localDate.format(formatter);
        return formattedDate;
    }

}
