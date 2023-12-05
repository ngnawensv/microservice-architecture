package com.belrose.microsvclistener.exception;

public class WriteJsonException extends RuntimeException {
    public WriteJsonException() {
    }

    public WriteJsonException(String message) {
        super(message);
    }

    public WriteJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriteJsonException(Throwable cause) {
        super(cause);
    }
}
