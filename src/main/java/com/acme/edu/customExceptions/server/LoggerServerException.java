package com.acme.edu.customExceptions.server;

public class LoggerServerException extends Exception {
    public LoggerServerException() {
        super();
    }

    public LoggerServerException(String message) {
        super(message);
    }

    public LoggerServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoggerServerException(Throwable cause) {
        super(cause);
    }

    protected LoggerServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
