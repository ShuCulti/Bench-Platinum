package com.platinumbench.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.platinumbench.logic.exception.DuplicateModelException;
import com.platinumbench.logic.exception.ModelNotFoundException;

@RestControllerAdvice 
public class GEH {
    @ExceptionHandler(DuplicateModelException.class)
    public ProblemDetail handleDuplicateModel(DuplicateModelException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail handleModelNotFound(ModelNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}

