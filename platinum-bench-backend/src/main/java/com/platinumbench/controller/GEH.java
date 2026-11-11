package com.platinumbench.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.platinumbench.logic.exception.DuplicateModelException;

@RestControllerAdvice 
public class GEH {
    @ExceptionHandler(DuplicateModelException.class)
        public ProblemDetail handleDuplicateModel(DuplicateModelException ex){
            return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        }
        

}

