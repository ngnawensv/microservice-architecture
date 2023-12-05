package com.belrose.agreservice.exception;

public class AgreServiceException extends RuntimeException{
    public AgreServiceException(String message) {
        super(message);
    }
    public AgreServiceException(String message, Throwable throwable) {
        super(message,throwable);
    }
    public AgreServiceException(Throwable throwable) {
        super(throwable);
    }
}


