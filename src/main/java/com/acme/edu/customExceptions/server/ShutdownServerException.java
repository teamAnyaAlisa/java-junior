package com.acme.edu.customExceptions.server;

public class ShutdownServerException extends LoggerServerException {
    public ShutdownServerException() {
        super();
    }

    public ShutdownServerException(String message) {
        super(message);
    }

    public ShutdownServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShutdownServerException(Throwable cause) {
        super(cause);
    }

    protected ShutdownServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
