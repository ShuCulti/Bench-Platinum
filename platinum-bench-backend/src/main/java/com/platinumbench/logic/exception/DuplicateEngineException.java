package com.platinumbench.logic.exception;

public class DuplicateEngineException extends RuntimeException{
    public DuplicateEngineException(String name, String version) {
        super("engine " + name  + " version " + version +  " Already exists");
    }
}
