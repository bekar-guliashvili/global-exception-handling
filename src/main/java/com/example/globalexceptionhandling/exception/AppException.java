package com.example.globalexceptionhandling.exception;

public class AppException extends Exception{

    private Integer code;
    private String message;

    public AppException(ErrorEnum error){
        super(error.getDesc());
        this.message = error.getDesc();
        this.code = error.getCode();
    }

    public Integer getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

}
