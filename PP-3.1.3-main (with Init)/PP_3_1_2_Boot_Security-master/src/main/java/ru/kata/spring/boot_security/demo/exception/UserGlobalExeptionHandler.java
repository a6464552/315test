package ru.kata.spring.boot_security.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExeptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> hExeption(NoSuchException exeption){
        UserIncorrectData userIncoredData = new UserIncorrectData();
        userIncoredData.setInfo(exeption.getMessage());
        return new ResponseEntity<>(userIncoredData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> hExeption(Exception exeption){
        UserIncorrectData userIncoredData = new UserIncorrectData();
        userIncoredData.setInfo(exeption.getMessage());
        return new ResponseEntity<>(userIncoredData, HttpStatus.BAD_REQUEST);
    }
}
