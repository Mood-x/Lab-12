package com.example.blog_security.Advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.blog_security.API.ApiException;

import jakarta.validation.UnexpectedTypeException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

        @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

    @ExceptionHandler(value = MissingPathVariableException.class)
    public ResponseEntity MissingPathVariableException(MissingPathVariableException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }
    

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }


    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity MissingServletRequestParameterException(MissingServletRequestParameterException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

    
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity NullPointerException(NullPointerException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

    @ExceptionHandler(value = UnexpectedTypeException.class)
    public ResponseEntity UnexpectedTypeException(UnexpectedTypeException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

    @ExceptionHandler(value = TransactionSystemException.class)
    public ResponseEntity TransactionSystemException(TransactionSystemException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    public ResponseEntity InvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

    @ExceptionHandler(value = ClassCastException.class)
    public ResponseEntity ClassCastException(ClassCastException e){
        return ResponseEntity.status(400).body(e.getMessage()); 
    }

}
