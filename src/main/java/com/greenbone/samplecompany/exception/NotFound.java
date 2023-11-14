package com.greenbone.samplecompany.exception;

public class NotFound extends RuntimeException {

    public NotFound(String message) {
        super(message);
    }
}
