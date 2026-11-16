package com.platinumbench.logic.exception;

public class EngineNotFoundException extends RuntimeException {
    public EngineNotFoundException(Long id) {
        super("Engine with id" + id + " could not be found");
    }
}
