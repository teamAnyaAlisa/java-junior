package com.acme.edu.customExceptions;

public class LogSaverException extends Exception {
    public LogSaverException() {
        super();
    }

    public LogSaverException(String message) {
        super(message);
    }

    public LogSaverException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogSaverException(Throwable cause) {
        super(cause);
    }

    protected LogSaverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
