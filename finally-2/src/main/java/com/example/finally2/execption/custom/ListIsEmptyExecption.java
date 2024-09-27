package com.example.finally2.execption.custom;

public class ListIsEmptyExecption extends RuntimeException{
    public ListIsEmptyExecption(String message){
        super(message);
    }
}
