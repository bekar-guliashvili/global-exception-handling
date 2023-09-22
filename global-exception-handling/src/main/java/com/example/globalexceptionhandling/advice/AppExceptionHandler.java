package com.example.globalexceptionhandling.advice;

import com.example.globalexceptionhandling.exception.AppException;
import com.example.globalexceptionhandling.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errorMap.put(err.getField(), err.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorMessage> handleUserException(AppException ex) {
        ErrorMessage message = ErrorMessage.builder()
                .timeStamp(LocalDateTime.now())
                .status(ex.getCode())
                .error(ex.getMessage())
                .build();
        return new ResponseEntity(message, HttpStatus.valueOf(ex.getCode()));
    }



}
