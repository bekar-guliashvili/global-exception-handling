package com.example.globalexceptionhandling.exception;

public enum ErrorEnum {
    Professor_NOT_FOUND("Professor NOT FOUND", 404),
    Professor_ALREADY_EXISTS("Professor Already Exists", 409),
    Student_NOT_FOUND("Student NOT FOUND", 404),
    Student_ALREADY_EXISTS("Student Already Exists", 409);


    private String desc;
    private Integer code;
    ErrorEnum(String desc, Integer code){
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public Integer getCode(){
        return this.code;
    }
}
