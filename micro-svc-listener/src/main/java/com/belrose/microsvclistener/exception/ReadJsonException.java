package com.belrose.microsvclistener.exception;

public class ReadJsonException extends RuntimeException {
    public ReadJsonException() {
    }

    public ReadJsonException(String message) {
        super(message);
    }

    public ReadJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadJsonException(Throwable cause) {
        super(cause);
    }
}
