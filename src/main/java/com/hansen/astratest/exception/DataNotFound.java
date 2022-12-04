package com.hansen.astratest.exception;

public class DataNotFound extends RuntimeException {

    public DataNotFound(String errorMessage) {
        super(errorMessage);
    }
}
