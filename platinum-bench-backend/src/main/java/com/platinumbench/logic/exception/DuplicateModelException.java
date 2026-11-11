package com.platinumbench.logic.exception;

public class DuplicateModelException extends RuntimeException{
    public DuplicateModelException(String name) {
        super("Model named " + name + " already exists");
    }
}
