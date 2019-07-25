package com.acme.edu.customExceptions.server;

public class FailEstablishConnectionException extends LoggerServerException {
    public FailEstablishConnectionException() {
        super();
    }

    public FailEstablishConnectionException(String message) {
        super(message);
    }

    public FailEstablishConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailEstablishConnectionException(Throwable cause) {
        super(cause);
    }

    protected FailEstablishConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
