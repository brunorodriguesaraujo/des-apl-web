package com.example.demoapp.exception.handler;

import com.example.demoapp.exception.MessageErrorException;
import com.example.demoapp.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageErrorException> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        MessageErrorException message = new MessageErrorException(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    };

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageErrorException> globalExceptionHandler(Exception ex, WebRequest request){
        MessageErrorException message = new MessageErrorException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    };

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageErrorException> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        MessageErrorException message = new MessageErrorException(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    };
}
