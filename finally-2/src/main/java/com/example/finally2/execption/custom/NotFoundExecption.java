package com.example.finally2.execption.custom;

public class NotFoundExecption extends RuntimeException{
    public NotFoundExecption(String message){
        super(message);
    }
}
