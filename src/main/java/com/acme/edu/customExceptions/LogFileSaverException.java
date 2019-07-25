package com.acme.edu.customExceptions;

public class LogFileSaverException extends LogSaverException {
    public LogFileSaverException() {
        super();
    }

    public LogFileSaverException(String message) {
        super(message);
    }

    public LogFileSaverException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogFileSaverException(Throwable cause) {
        super(cause);
    }

    protected LogFileSaverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
