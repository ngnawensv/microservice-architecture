package com.belrose.microsvclistener.exception;

public class MicroSvcListenerException  extends  RuntimeException{
    public MicroSvcListenerException() {
    }

    public MicroSvcListenerException(String message) {
        super(message);
    }

    public MicroSvcListenerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MicroSvcListenerException(Throwable cause) {
        super(cause);
    }

    public MicroSvcListenerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
