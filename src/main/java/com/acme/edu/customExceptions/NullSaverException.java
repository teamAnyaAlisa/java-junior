package com.acme.edu.customExceptions;

public class NullSaverException extends LogSaverException {
    public NullSaverException() {
        super();
    }

    public NullSaverException(String message) {
        super(message);
    }

    public NullSaverException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullSaverException(Throwable cause) {
        super(cause);
    }

    protected NullSaverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
