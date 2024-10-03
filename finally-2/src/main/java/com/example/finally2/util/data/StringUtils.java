package com.example.finally2.util.data;

public class StringUtils {

    public static String replace(String input){
        if (input != null) {
            input = input.replace("%", "\\%").replace("_", "\\_");
            return "%"+input+"%";
        }else{
            return null;
        }
    }
}
