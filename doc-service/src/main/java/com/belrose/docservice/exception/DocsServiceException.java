package com.belrose.docservice.exception;

public class DocsServiceException extends RuntimeException {
    public DocsServiceException(String message) {
        super(message);
    }
    public DocsServiceException(String message, Throwable throwable) {
        super(message,throwable);
    }
    public DocsServiceException(Throwable throwable) {
        super(throwable);
    }
}
